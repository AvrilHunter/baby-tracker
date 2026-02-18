package dev.ahunter.baby_tracker.controller;

import dev.ahunter.baby_tracker.entity.Feed;
import dev.ahunter.baby_tracker.model.CreateFeedRequest;
import dev.ahunter.baby_tracker.service.FeedingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedingController {

    private final FeedingService feedingService;

    @Autowired
    public FeedingController(FeedingService feedingService){
        this.feedingService = feedingService;
    }

    @GetMapping("/")
    public String index() {
        return "Endpoints available at GET /feed, POST /feed";
    }

    @GetMapping("/feeds")
    public ResponseEntity<Iterable<Feed>> getAllFeeds() {
        return new ResponseEntity<>(feedingService.getAllFeeds(), HttpStatus.OK);
    }

    @PostMapping("/feed")
    public ResponseEntity<Feed> saveFeed(@Valid @RequestBody CreateFeedRequest newFeed){
        return new ResponseEntity<>(feedingService.saveFeed(newFeed),HttpStatus.CREATED);
    }
}