package com.example.JSON_Feed_Server.services;

import com.example.JSON_Feed_Server.dto.FeedObject;
import com.example.JSON_Feed_Server.dto.PostObject;
import com.example.JSON_Feed_Server.dto.XkcdObject;
import com.example.JSON_Feed_Server.models.FeedModel;
import com.example.JSON_Feed_Server.models.PostModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class XkcdService {
    @Value("${base.url}")
    private String baseUrl;
    private final String xkcdFeedUrl = "https://xkcd.com/info.0.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final FeedService feedService;
    private final HttpRequestService httpRequestService;

    //@Autowired is not needed if there is one existing constructor for a Service class
    //Could also use autowired with a field?? but is not recommended because testing apparently becomes difficult
    @Autowired
    public XkcdService(FeedService feedService, HttpRequestService httpRequestService) {
        this.feedService = feedService;
        this.httpRequestService = httpRequestService;
    }

    public PostObject createPostObject(XkcdObject xkcdObject) {
        String url = "https://xkcd.com/" + xkcdObject.num;

        LocalDateTime dateTime = LocalDateTime.of(
                Integer.parseInt(xkcdObject.year),
                Integer.parseInt(xkcdObject.month),
                Integer.parseInt(xkcdObject.day),
                4, 0);
        String isoString = dateTime.atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);

        String descriptionTemplate = String.format(
                "<div><img src=%s /><p>%s</p>" +
                        "<a href=\"https://www.explainxkcd.com/%s\">Explanation</a><p>%s</p></div>", xkcdObject.img, xkcdObject.alt, xkcdObject.num, xkcdObject.news
        );

        return new PostObject(
                xkcdObject.title,
                url,
                url,
                xkcdObject.alt,
                isoString,
                descriptionTemplate,
                xkcdObject.img
        );
    }

    public FeedObject createJsonFeed(PostObject postObject) {
        String baseUrl = this.baseUrl;
        String title = "xkcd";
        String homePageUrl = "https://xkcd.com";
        String feedUrl = baseUrl + "/articles/xkcd";
        String description = "A webcomic of romance, sarcasm, math, and language.";
        String icon = baseUrl + "/xkcd_icon.png";
        String favicon = baseUrl + "/xkcd_icon_64.png";
        List<PostObject> items = new ArrayList<>();
        items.add(postObject);

        return new FeedObject(title, homePageUrl, feedUrl, description, icon, favicon, items);
    }

    public PostObject fetchXkcdPost() {
        PostObject output = null;

        try {
            HttpResponse<String> response = httpRequestService.getResponseFromUrl(xkcdFeedUrl);
            String responseBody = response.body();

            if (!httpRequestService.isSuccessful(response)) {
                System.err.println("Error: Received HTTP status " + response.statusCode());
                System.err.println("Response body: " + responseBody);
                return null;
            }

            XkcdObject xkcdObject = objectMapper.readValue(responseBody, XkcdObject.class);
            output = createPostObject(xkcdObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public FeedObject getJsonFeed() {
        FeedObject jsonFeed = null;

        FeedModel feedModel = feedService.getFeed(xkcdFeedUrl);
        PostObject xkcdItem = fetchXkcdPost();

        Boolean postAlreadyExists = feedService.postAlreadyExists(xkcdItem.miniToString());

        if (!postAlreadyExists) {
            long count = feedService.getPostsCountForFeed(xkcdFeedUrl);
            System.out.println(count + " posts in db");
            if (count == 10) {
                // TODO if count == 10, delete oldest (last?) item from db
                PostModel oldestPostModel = feedService.getOldestPostForFeed(xkcdFeedUrl);
                System.out.println("Oldest post: " + oldestPostModel.miniToString());
                feedService.deleteOldestPost(oldestPostModel.getUrl());
            }

            PostModel newPostModel = feedService.mapPostObjectToPost(xkcdItem, feedModel);
            System.out.println("New post: " + newPostModel.miniToString());
            feedService.savePost(newPostModel);
        }

        List<PostModel> postModels = feedService.getPostsForFeed(xkcdFeedUrl);
//        TODO make into FeedObject somehow?
//        return FeedObject

        return jsonFeed;
    }

}
