package com.example.JSON_Feed_Server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@RestController
public class XkcdController {
    private final String BASE_URL = "http://localhost:8080";

    private JsonFeedObject getJson() {
        XkcdObject data_obj = null;
        JsonFeedObject output = null;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://xkcd.com/info.0.json"))
                .GET()
                .build();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            XkcdObject xkcdObject = objectMapper.readValue(responseBody, XkcdObject.class);

            String url = "https://xkcd.com/" + xkcdObject.num;
            LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(xkcdObject.year), Integer.parseInt(xkcdObject.month), Integer.parseInt(xkcdObject.day), 4, 0);
            String isoString = dateTime.atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
            String descriptionTemplate = String.format("<div><img src=%s /><p>%s</p>" +
                    "<a href=\"https://www.explainxkcd.com/%s\">Explanation</a><p>%s</p></div>", xkcdObject.img, xkcdObject.alt, xkcdObject.num, xkcdObject.news);

            FeedItem feedItem = new FeedItem(xkcdObject.title, url, url, xkcdObject.alt, isoString, descriptionTemplate, xkcdObject.img);

            String title = "xkcd";
            String homePageUrl = "https://xkcd.com";
            String feedUrl = BASE_URL + "/articles/xkcd";
            String description = "A webcomic of romance, sarcasm, math, and language.";
            String icon = BASE_URL + "/static/xkcd_icon.png";
            String favicon = BASE_URL + "/static/xkcd_icon_64.png";
            List<FeedItem> items = new ArrayList<>();
            items.add(feedItem);

            output = new JsonFeedObject(title, homePageUrl, feedUrl, description, icon, favicon, items);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    @GetMapping("/xkcd")
    public JsonFeedObject getXkcd() {
        return getJson();
    }
}