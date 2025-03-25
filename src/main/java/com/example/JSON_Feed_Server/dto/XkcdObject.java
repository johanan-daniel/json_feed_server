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
        return "XkcdObject{" +
                "\n\tmonth='" + month + '\'' +
                ",\n\t num=" + num +
                ",\n\t link='" + link + '\'' +
                ",\n\t year='" + year + '\'' +
                ",\n\t news='" + news + '\'' +
                ",\n\t safeTitle='" + safeTitle + '\'' +
                ",\n\t transcript='" + transcript + '\'' +
                ",\n\t alt='" + alt + '\'' +
                ",\n\t img='" + img + '\'' +
                ",\n\t title='" + title + '\'' +
                ",\n\t day='" + day + '\'' +
                "\n}";
    }

    public String miniToString() {
        return "XkcdObject{num=" + num + "}";
    }
}
