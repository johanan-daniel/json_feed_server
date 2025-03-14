package com.example.JSON_Feed_Server.controllers;

import com.example.JSON_Feed_Server.dto.JsonFeedObject;
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
    public JsonFeedObject getXkcd() {
        return xkcdService.fetchXkcdData();
    }
}