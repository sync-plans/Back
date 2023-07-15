package com.planning.plan.dto;

import com.planning.plan.entity.TeamPlan;
import com.planning.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class TeamPlanDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;
    private TeamDto teamDto;
    private User user;

    public TeamPlanDto(TeamPlan teamPlan) {
        this.id = teamPlan.getId();
        this.title = teamPlan.getTitle();
        this.content = teamPlan.getContent();
        this.startDate = teamPlan.getStartDate();
        this.endDate = teamPlan.getEndDate();
        this.priority = teamPlan.getPriority();
        this.teamDto = new TeamDto(teamPlan.getTeam());
        this.user = teamPlan.getUser();
    }
}
