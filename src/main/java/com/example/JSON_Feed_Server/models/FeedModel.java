package com.example.JSON_Feed_Server.models;

/*Source: https://vladmihalcea.com/how-to-map-json-objects-using-generic-hibernate-types/*/
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Map;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Feeds") // Specifies table name if it differs from class name
public class FeedModel {
    private String title;

    // Specifies column name if it differs from field name
    @Column(name = "home_page_url")
    private String homePageUrl;

    // Specifies this is the primary key?
    @Id
    @Column(name = "feed_url")
    private String feedUrl;

    private String description;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<Map<String, String>> authors;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "favicon_url")
    private String faviconUrl;

    // , cascade = CascadeType.ALL, orphanRemoval = true
    // connects to feed field in PostObject.java
    // WARNING: if postModels is included in this toString and feed is included in Posts' toString
    // an infinite loop will occur because each toString call will call toString on the other object
    @OneToMany(mappedBy = "feed")
    private List<PostModel> posts;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHomePageUrl() {
        return homePageUrl;
    }

    public void setHomePageUrl(String homePageUrl) {
        this.homePageUrl = homePageUrl;
    }

    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Map<String, String>> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Map<String, String>> authors) {
        this.authors = authors;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getFaviconUrl() {
        return faviconUrl;
    }

    public void setFaviconUrl(String faviconUrl) {
        this.faviconUrl = faviconUrl;
    }

    public List<PostModel> getPosts() {
        return posts;
    }

    public void setPosts(List<PostModel> postModels) {
        this.posts = postModels;
    }

    @Override
    public String toString() {
        return "FeedModel{" +
                "title='" + title + '\'' +
                ", homePageUrl='" + homePageUrl + '\'' +
                ", feedUrl='" + feedUrl + '\'' +
                ", description='" + description + '\'' +
                ", authors=" + authors +
                ", iconUrl='" + iconUrl + '\'' +
                ", faviconUrl='" + faviconUrl + '\'' +
//                DO NOT include BOTH fields connected by a @*ToMany ? because will lead to stack overflow
//                ", postModels=" + postModels +
                '}';
    }

    public String miniToString() {
        return "FeedModel{feedUrl='" + feedUrl + "'}";
    }
}