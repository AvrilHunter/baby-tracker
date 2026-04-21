package dev.ahunter.baby_tracker.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record CreateFeedRequest(
    @NotNull(message = "must have date")
    LocalDate date,
    @NotNull(message = "must have start time")
    LocalTime startTime,
    @NotNull(message = "must have end time")
    LocalTime endTime,
    String comments
) {}