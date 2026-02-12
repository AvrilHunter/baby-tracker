package dev.ahunter.baby_tracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedingController {

    @GetMapping("/")
    public String index() {
        return "Endpoints available at GET /feed, POST /feed";
    }

    @GetMapping("/feed")
    public String feed() {
        return "GET all feeding logs";
    }

}