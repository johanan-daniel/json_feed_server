package com.example.JSON_Feed_Server.repositories;

import com.example.JSON_Feed_Server.models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostModel, String> {
    List<PostModel> findByFeedFeedUrl(String feedUrl);

    List<PostModel> findTop10ByFeedFeedUrlOrderByDatePublishedDesc(String feedUrl);

    long countByFeedFeedUrl(String feedUrl);

    PostModel findByUrl(String postUrl);

//    @Query("SELECT p from PostModel p WHERE p.feed.feed")
    PostModel findFirstByFeedFeedUrlOrderByDatePublishedDesc(String feedUrl);

    void deleteByUrl(String postUrl);
}
