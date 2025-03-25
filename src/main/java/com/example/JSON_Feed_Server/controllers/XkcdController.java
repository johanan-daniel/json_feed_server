package com.example.JSON_Feed_Server.controllers;

import com.example.JSON_Feed_Server.dto.FeedObject;
import com.example.JSON_Feed_Server.services.XkcdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XkcdController {
    private final XkcdService xkcdService;

    @Autowired
    public XkcdController(XkcdService xkcdService) {
        this.xkcdService = xkcdService;
    }

    @GetMapping("/xkcd")
    public FeedObject getXkcd() {
//        return xkcdService.fetchXkcdPost();
        FeedObject jsonFeed = xkcdService.getJsonFeed();
        if (jsonFeed == null) {
            System.err.println("A problem occurred while processing the feed");
            return null;
        }

        return jsonFeed;
    }
}