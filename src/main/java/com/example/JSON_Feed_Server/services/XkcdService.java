package com.example.JSON_Feed_Server.services;

import com.example.JSON_Feed_Server.dto.FeedItem;
import com.example.JSON_Feed_Server.dto.JsonFeedObject;
import com.example.JSON_Feed_Server.dto.XkcdObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class XkcdService {
    private final JsonFeedService jsonFeedService;

    @Autowired
    public XkcdService(JsonFeedService jsonFeedService) {
        this.jsonFeedService = jsonFeedService;
    }

    private static final String XKCD_API_URL = "https://xkcd.com/info.0.json";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

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

            FeedItem feedItem = jsonFeedService.createFeedItem(xkcdObject);
            output = jsonFeedService.createJsonFeed(feedItem);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

}
