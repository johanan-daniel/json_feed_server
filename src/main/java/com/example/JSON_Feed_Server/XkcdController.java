package com.example.JSON_Feed_Server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class XkcdController {
    private JsonNode sendRequest() {
        JsonNode output = null;
        String URL = "https://xkcd.com/info.0.json";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            JsonNode rootNode = objectMapper.readTree(responseBody);
//            String alt = rootNode.get("alt").asText();
            output = rootNode;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    @GetMapping("/xkcd")
    public JsonNode getXkcd() {
        return sendRequest();
    }
}
