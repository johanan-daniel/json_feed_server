package com.example.JSON_Feed_Server.repositories;

import com.example.JSON_Feed_Server.models.FeedModel;
import org.springframework.data.jpa.repository.JpaRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface FeedRepository extends JpaRepository<FeedModel, String> {
    FeedModel findByFeedUrl(String feedUrl);
}
