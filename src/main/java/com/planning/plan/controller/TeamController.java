package com.planning.plan.controller;

import com.planning.plan.dto.TeamInviteDto;
import com.planning.plan.dto.TeamPlannerCreateDto;
import com.planning.plan.dto.TeamPlanDto;
import com.planning.plan.dto.TeamUserDto;
import com.planning.plan.service.TeamService;
import com.planning.security.UserDetailsImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
@Slf4j(topic = "Team Controller")
public class TeamController {
    @NonNull
    private TeamService teamService;

    @PostMapping("")
    public TeamUserDto createTeamPlanner(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody TeamPlannerCreateDto requestDto) {
        return this.teamService.createTeamPlanner(requestDto, userDetails.getUser());
    }

    @GetMapping("plan/{teamId}")
    public List<TeamPlanDto> getAllTeamPlan(@PathVariable Long teamId) {
        return this.teamService.getAllTeamPlan(teamId);
    }

    @PostMapping("/{teamId}/invite")
    public TeamUserDto inviteToTeam(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long teamId, @RequestBody TeamInviteDto requestDto){
        return this.teamService.inviteToTeam(teamId, requestDto, userDetails.getUser());
    }
}
