package com.example.JSON_Feed_Server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedObject {
    public final String version = "https://jsonfeed.org/version/1.1";
    public final String language = "en-US";
    public String title;

    @JsonProperty("home_page_url")
    public String homePageUrl;

    @JsonProperty("feed_url")
    public String feedUrl;

    public String description;
    public String icon;
    public String favicon;
    public List<PostObject> items;
    public List<Map<String, String>> authors;

    public FeedObject(String title, String homePageUrl, String feedUrl, String description, List<Map<String, String>> authors, String icon, String favicon, List<PostObject> items) {
        this.title = title;
        this.homePageUrl = homePageUrl;
        this.feedUrl = feedUrl;
        this.description = description;
        this.icon = icon;
        this.favicon = favicon;
        this.items = items;
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "FeedObject{" +
                "title='" + title + '\'' +
                ", homePageUrl='" + homePageUrl + '\'' +
                ", feedUrl='" + feedUrl + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", favicon='" + favicon + '\'' +
                ", items=" + (items != null ? items.size() + " items" : "null") +
                '}';
    }

    public String miniToString() {
        return "FeedObject{url='" + feedUrl + "'}";
    }
}
