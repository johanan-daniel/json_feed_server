package com.example.JSON_Feed_Server.services;

import com.example.JSON_Feed_Server.dto.FeedObject;
import com.example.JSON_Feed_Server.dto.PostObject;
import com.example.JSON_Feed_Server.models.FeedModel;
import com.example.JSON_Feed_Server.models.PostModel;
import com.example.JSON_Feed_Server.repositories.FeedRepository;
import com.example.JSON_Feed_Server.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FeedService {
    private final FeedRepository feedRepository;
    private final PostRepository postRepository;

    @Autowired
    public FeedService(FeedRepository feedRepository, PostRepository postRepository) {
        this.feedRepository = feedRepository;
        this.postRepository = postRepository;
    }

    public FeedModel getFeed(String feedUrl) {
        return feedRepository.findByFeedUrl(feedUrl);
    }

    public List<FeedModel> getAllFeeds() {
        return feedRepository.findAll();
    }

    public PostModel savePost(PostModel postModel) {
        return postRepository.save(postModel);
    }

    public Boolean postAlreadyExists(String postUrl) {
        PostModel postModelFromDb = postRepository.findByUrl(postUrl);
        return postModelFromDb != null;
    }

    public List<PostModel> getPostsForFeed(String feedUrl) {
        return postRepository.findByFeedFeedUrlOrderByDatePublishedDesc(feedUrl);
    }

    public long getPostsCountForFeed(String feedUrl) {
        return postRepository.countByFeedFeedUrl(feedUrl);
    }

    public PostModel getOldestPostForFeed(String feedUrl) {
        return postRepository.findFirstByFeedFeedUrlOrderByDatePublishedDesc(feedUrl);
    }

    // In order to call a delete (or update) method from a service, it needs to be marked as transactional
    @Transactional
    public void deleteOldestPost(String postUrl){
        postRepository.deleteByUrl(postUrl);
    }

    public PostModel mapPostObjectToPostModel(PostObject postObject, FeedModel feedModel) {
        PostModel postModel = new PostModel();

        postModel.setFeed(feedModel);

        postModel.setTitle(postObject.title);

        postModel.setAuthors(null);

        postModel.setUrl(postObject.url);

        postModel.setSummary(postObject.summary);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime dateTime = LocalDateTime.parse(postObject.datePublished, formatter);
        postModel.setDatePublished(dateTime);

        postModel.setContent_html(postObject.contentHtml);

        postModel.setImageUrl(postObject.image);

        return postModel;
    }

    public PostObject mapPostModelToPostObject(PostModel postModel) {
        return new PostObject(
                postModel.getTitle(),
                postModel.getUrl(),
                postModel.getSummary(),
                postModel.getDatePublished().format(DateTimeFormatter.ISO_DATE_TIME),
                postModel.getContent_html(),
                postModel.getImageUrl()
        );
    }

    public FeedObject mapFeedModelAndPostsToFeedObject(FeedModel feedModel, List<PostModel> posts) {
        List<PostObject> postObjects = posts.stream().map(this::mapPostModelToPostObject).toList();

        return new FeedObject(
                feedModel.getTitle(),
                feedModel.getHomePageUrl(),
                feedModel.getFeedUrl(),
                feedModel.getDescription(),
                feedModel.getAuthors(),
                feedModel.getIconUrl(),
                feedModel.getFaviconUrl(),
                postObjects
        );
    }
}
