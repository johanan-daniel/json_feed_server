package com.example.JSON_Feed_Server.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class HttpRequestService {
    private final HttpClient client = HttpClient.newHttpClient();

    public HttpResponse<String> getResponseFromUrl(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public Boolean isSuccessful(HttpResponse<String> response) {
        return response.statusCode() < 200 || response.statusCode() >= 300;
    }
}
