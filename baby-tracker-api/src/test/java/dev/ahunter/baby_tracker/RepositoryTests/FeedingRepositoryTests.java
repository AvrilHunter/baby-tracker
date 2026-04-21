package dev.ahunter.baby_tracker.RepositoryTests;

import dev.ahunter.baby_tracker.entity.Feed;
import dev.ahunter.baby_tracker.repository.FeedingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FeedingRepositoryTests {

@Autowired
    FeedingRepository feedingRepository;

@Autowired
    TestEntityManager entityManager;

private final Feed newFeed = Feed.builder()
        .date(LocalDate.parse("2026-03-02"))
        .startTime(LocalTime.parse("19:00"))
        .endTime(LocalTime.parse("19:45"))
        .build();

@Test
    void givenNewFeed_whenSaved_success(){
    Feed savedFeed = feedingRepository.save(newFeed);
    assertThat(entityManager.find(Feed.class, savedFeed.getId())).isEqualTo(newFeed);
}

}
