package com.planning.plan.dto;

import com.planning.plan.entity.Team;
import com.planning.plan.entity.TeamUser;
import com.planning.user.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TeamUserDto {
    private Team team;
    private UserDto userDto;

    public TeamUserDto(TeamUser teamUser){
        this.team = teamUser.getTeam();
        this.userDto = new UserDto(teamUser.getUser());
    }
}
