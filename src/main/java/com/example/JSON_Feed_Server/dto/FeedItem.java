package com.example.JSON_Feed_Server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedItem {
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

    @Override
    public String toString() {
        return "FeedItem{" +
                "title='" + title + '\'' + '\n' +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                ", summary='" + summary + '\'' +
                ", datePublished='" + datePublished + '\'' +
                ", contentHtml='" + (contentHtml != null ? contentHtml.substring(0, Math.min(contentHtml.length(), 50)) + "..." : "null") + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
