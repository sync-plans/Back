package com.planning.plan.entity;

import com.planning.plan.dto.CreateTeamPlannerDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String teamTitle;

    public Team(CreateTeamPlannerDto requestDto) {
        this.teamTitle = requestDto.getTeamTitle();
    }
}
