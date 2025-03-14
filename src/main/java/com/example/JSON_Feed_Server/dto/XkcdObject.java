package com.example.JSON_Feed_Server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class XkcdObject {
    public String month;
    public int num;
    public String link;
    public String year;
    public String news;
    @JsonProperty("safe_title")
    public String safeTitle;
    public String transcript;
    public String alt;
    public String img;
    public String title;
    public String day;

    @Override
    public String toString() {
        return "XKCDComic{" +
                "month='" + month + '\'' +
                ", num=" + num +
                ", link='" + link + '\'' +
                ", year='" + year + '\'' +
                ", news='" + news + '\'' +
                ", safeTitle='" + safeTitle + '\'' +
                ", transcript='" + transcript + '\'' +
                ", alt='" + alt + '\'' +
                ", img='" + img + '\'' +
                ", title='" + title + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
