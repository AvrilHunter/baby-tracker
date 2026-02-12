package dev.ahunter.baby_tracker.service;

import dev.ahunter.baby_tracker.entity.Feed;
import dev.ahunter.baby_tracker.model.CreateFeedRequest;
import dev.ahunter.baby_tracker.repository.FeedingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedingService {

    private final FeedingRepository feedingRepository;

    @Autowired
    public FeedingService(FeedingRepository feedingRepository){
        this.feedingRepository = feedingRepository;
    }

    public Iterable<Feed> getAllFeeds(){
        return feedingRepository.findAll();
    }

    public Feed saveFeed(CreateFeedRequest feedInfo) {
        Feed newFeedInfo = Feed.builder()
                .date(feedInfo.date())
                .startTime(feedInfo.startTime())
                .endTime(feedInfo.endTime())
                .comments(feedInfo.comments())
                .build();

        return feedingRepository.save(newFeedInfo);
    }
}
