package dev.ahunter.baby_tracker.model;

import jakarta.validation.constraints.NotEmpty;

public record CreateFeedRequest(
    @NotEmpty(message = "must have date")
    String date,
    @NotEmpty(message = "must have start time")
    String startTime,
    @NotEmpty(message = "must have end time")
    String endTime,
    String comments
) {}