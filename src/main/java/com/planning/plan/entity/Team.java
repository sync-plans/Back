package com.planning.plan.entity;

import com.planning.plan.dto.TeamPlannerCreateDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@Getter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String teamTitle;

    public Team(TeamPlannerCreateDto requestDto) {
        this.teamTitle = requestDto.getTeamTitle();
    }
}
