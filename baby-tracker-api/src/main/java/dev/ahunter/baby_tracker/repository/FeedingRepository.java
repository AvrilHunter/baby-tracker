package dev.ahunter.baby_tracker.repository;

import dev.ahunter.baby_tracker.entity.Feed;
import org.springframework.data.repository.CrudRepository;

public interface FeedingRepository extends CrudRepository<Feed, Integer> {
}
