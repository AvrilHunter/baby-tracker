package dev.ahunter.baby_tracker.controllerTests;

import dev.ahunter.baby_tracker.controller.FeedingController;
import dev.ahunter.baby_tracker.entity.Feed;
import dev.ahunter.baby_tracker.model.CreateFeedRequest;
import dev.ahunter.baby_tracker.service.FeedingService;

//check which should be static
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@WebMvcTest(FeedingController.class)
@ExtendWith(SpringExtension.class)
public class FeedingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    FeedingService feedingService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final CreateFeedRequest feedRequest = CreateFeedRequest.builder()
            .date(LocalDate.parse("2026-03-02"))
            .startTime(LocalTime.parse("19:00"))
            .endTime(LocalTime.parse("19:45"))
            .build();

    private final Feed feed = Feed.builder()
            .date(LocalDate.parse("2026-03-02"))
            .startTime(LocalTime.parse("19:00"))
            .endTime(LocalTime.parse("19:45"))
            .id(1)
            .build();


    @Test
    void feeding_controller_home_successful() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Endpoints available at GET /feed, POST /feed"));
    }

    @Test
    void feeding_controller_getAllFeeds_none_found() throws Exception {
        ArrayList<Feed> allFeeds = new ArrayList<>();
        when(feedingService.getAllFeeds()).thenReturn(allFeeds);
        this.mockMvc.perform(get("/feeds"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(allFeeds)));
    }

    @Test
    void feeding_controller_getAllFeeds() throws Exception {
        ArrayList<Feed> allFeeds = new ArrayList<>();
        allFeeds.add(feed);

        when(feedingService.getAllFeeds()).thenReturn(allFeeds);

        this.mockMvc.perform(get("/feeds"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(allFeeds)));
    }

    @Test
    void feeding_controller_post_feed_successful() throws Exception {

        when(feedingService.saveFeed(feedRequest)).thenReturn(feed);

        this.mockMvc.perform(post("/feed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(feedRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(feed)));
    }

    @Test
    void feeding_controller_post_missing_field_unsuccessful() throws Exception {
        CreateFeedRequest invalidFeedRequest = CreateFeedRequest.builder()
                .date(LocalDate.parse("2026-03-02"))
                .startTime(LocalTime.parse("19:00"))
                .build();

        this.mockMvc.perform(post("/feed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidFeedRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void feeding_controller_post_incorrect_type_unsuccessful() throws Exception {
        String invalidJson = """
        {
          "date": "2026-03-02",
          "startTime": "19:00",
          "endTime": "19.45",
          "comments": "test"
        }
        """;

        this.mockMvc.perform(post("/feed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidJson)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
