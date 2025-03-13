package com.example.JSON_Feed_Server;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonFeedObject {
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
    public List<FeedItem> items;

    public JsonFeedObject(String title, String homePageUrl, String feedUrl, String description, String icon, String favicon, List<FeedItem> items) {
        this.title = title;
        this.homePageUrl = homePageUrl;
        this.feedUrl = feedUrl;
        this.description = description;
        this.icon = icon;
        this.favicon = favicon;
        this.items = items;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class FeedItem {
    public String title;
    public String url;
    public String id;
    public String summary;

    @JsonProperty("date_published")
    public String datePublished;

    @JsonProperty("content_html")
    public String contentHtml;

    public String image;

    public FeedItem(String title, String url, String id, String summary, String datePublished, String contentHtml, String image) {
        this.title = title;
        this.url = url;
        this.id = id;
        this.summary = summary;
        this.datePublished = datePublished;
        this.contentHtml = contentHtml;
        this.image = image;
    }
}