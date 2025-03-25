package com.example.JSON_Feed_Server.models;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Posts")
public class PostModel {

    // referencedColumnName = "feed_url",
    @ManyToOne
    @JoinColumn(name = "feed_id", nullable = false)
    private FeedModel feed;

    private String title;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<Map<String, String>> authors;

    @Id
    private String url;

    private String summary;

    @Column(name = "date_published")
    private LocalDateTime datePublished;

//    @Column(columnDefinition = "TEXT")
    private String content_html;

    @Column(name = "image_url")
    private String imageUrl;


    public FeedModel getFeed() {
        return feed;
    }

    public void setFeed(FeedModel feedModel) {
        this.feed = feedModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Map<String, String>> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Map<String, String>> authors) {
        this.authors = authors;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public LocalDateTime getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDateTime datePublished) {
        this.datePublished = datePublished;
    }

    public String getContent_html() {
        return content_html;
    }

    public void setContent_html(String content_html) {
        this.content_html = content_html;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String miniToString() {
        return "PostModel{url='" + url + "'}";
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "\n\t feedModel=" + feed +
                ",\n\t title='" + title + '\'' +
                ",\n\t authors=" + authors +
                ",\n\t url='" + url + '\'' +
                ",\n\t summary='" + summary + '\'' +
                ",\n\t datePublished=" + datePublished +
                ",\n\t content_html='" + content_html + '\'' +
                ",\n\t imgUrl='" + imageUrl + '\'' +
                "\n}";
    }
}
