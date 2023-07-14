package com.planning.dto;

import com.planning.entity.MyPlan;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanResponseDto {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;
    private String title;
    private String content;

    public PlanResponseDto(MyPlan myPlan){
        this.id = myPlan.getPlanId();
        this.startDate = myPlan.getStartDate();
        this.endDate = myPlan.getEndDate();
        this.priority = myPlan.getPriority();
        this.title = myPlan.getTitle();
        this.content = myPlan.getContent();
    }
}
