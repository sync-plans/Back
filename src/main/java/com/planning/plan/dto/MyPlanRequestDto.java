package com.planning.plan.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyPlanRequestDto {
    private Integer priority;
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
