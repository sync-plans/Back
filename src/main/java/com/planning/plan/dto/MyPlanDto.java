package com.planning.plan.dto;

import com.planning.plan.entity.MyPlan;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyPlanDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;

    public MyPlanDto(MyPlan myPlan){
        this.id = myPlan.getPlanId();
        this.startDate = myPlan.getStartDate();
        this.endDate = myPlan.getEndDate();
        this.priority = myPlan.getPriority();
        this.title = myPlan.getTitle();
        this.content = myPlan.getContent();
    }
}
