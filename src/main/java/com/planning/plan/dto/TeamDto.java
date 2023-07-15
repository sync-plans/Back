package com.planning.plan.dto;

import com.planning.plan.entity.Team;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TeamDto {
    private String teamTitle;
    public TeamDto(Team team) {
        this.teamTitle = team.getTeamTitle();
    }
}
