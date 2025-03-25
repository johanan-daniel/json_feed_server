package com.example.JSON_Feed_Server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostObject {
    public String title;
    public String url;
    public String id;
    public String summary;

    @JsonProperty("date_published")
    public String datePublished;

    @JsonProperty("content_html")
    public String contentHtml;

    public String image;


    public PostObject(String title, String url, String id, String summary, String datePublished, String contentHtml, String image) {
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
        return "PostObject{" +
                "\n\t title='" + title + '\'' +
                ",\n\t url='" + url + '\'' +
                ",\n\t id='" + id + '\'' +
                ",\n\t summary='" + summary + '\'' +
                ",\n\t datePublished='" + datePublished + '\'' +
                ",\n\t contentHtml='" + (contentHtml != null ? contentHtml.substring(0, Math.min(contentHtml.length(), 50)) + "..." : "null") + '\'' +
                ",\n\t image='" + image + '\'' +
                "\n}";
    }

    public String miniToString() {
        return "PostObject{url='" + url + "'}";
    }
}
