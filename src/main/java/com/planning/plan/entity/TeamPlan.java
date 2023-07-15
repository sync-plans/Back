package com.planning.plan.entity;

import com.planning.entity.TimeStamped;
import com.planning.plan.dto.TeamPlanCreateDto;
import com.planning.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@NoArgsConstructor
@Setter
public class TeamPlan extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(length = 500)
    private String content;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column
    private Integer priority;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public TeamPlan(TeamPlanCreateDto requestDto, Team team, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.startDate = requestDto.getStartDate();
        this.endDate = requestDto.getEndDate();
        this.priority = requestDto.getPriority();
    }

    public void update(TeamPlanCreateDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.startDate = requestDto.getStartDate();
        this.endDate = requestDto.getEndDate();
        this.priority = requestDto.getPriority();
    }
}
