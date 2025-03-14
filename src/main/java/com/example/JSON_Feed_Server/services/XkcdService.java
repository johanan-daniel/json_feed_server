package com.example.JSON_Feed_Server.services;

import com.example.JSON_Feed_Server.dto.FeedItem;
import com.example.JSON_Feed_Server.dto.JsonFeedObject;
import com.example.JSON_Feed_Server.dto.XkcdObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
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

    private static final String XKCD_API_URL = "https://xkcd.com/info.0.json";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FeedItem createFeedItem(XkcdObject xkcdObject) {
        String url = "https://xkcd.com/" + xkcdObject.num;
        LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(xkcdObject.year), Integer.parseInt(xkcdObject.month), Integer.parseInt(xkcdObject.day), 4, 0);
        String isoString = dateTime.atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
        String descriptionTemplate = String.format("<div><img src=%s /><p>%s</p>" +
                "<a href=\"https://www.explainxkcd.com/%s\">Explanation</a><p>%s</p></div>", xkcdObject.img, xkcdObject.alt, xkcdObject.num, xkcdObject.news);

        return new FeedItem(xkcdObject.title, url, url, xkcdObject.alt, isoString, descriptionTemplate, xkcdObject.img);
    }

    public JsonFeedObject createJsonFeed(FeedItem feedItem) {
        String baseUrl = this.baseUrl;
        String title = "xkcd";
        String homePageUrl = "https://xkcd.com";
        String feedUrl = baseUrl + "/articles/xkcd";
        String description = "A webcomic of romance, sarcasm, math, and language.";
        String icon = baseUrl + "/xkcd_icon.png";
        String favicon = baseUrl + "/xkcd_icon_64.png";
        List<FeedItem> items = new ArrayList<>();
        items.add(feedItem);

        return new JsonFeedObject(title, homePageUrl, feedUrl, description, icon, favicon, items);
    }

    public JsonFeedObject fetchXkcdData() {
        JsonFeedObject output = null;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(XKCD_API_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            XkcdObject xkcdObject = objectMapper.readValue(responseBody, XkcdObject.class);

            FeedItem feedItem = createFeedItem(xkcdObject);
            output = createJsonFeed(feedItem);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

}
