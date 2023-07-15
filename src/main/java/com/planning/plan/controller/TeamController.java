package com.planning.plan.controller;

import com.planning.plan.dto.CreateTeamPlannerDto;
import com.planning.plan.dto.TeamPlanDto;
import com.planning.plan.service.TeamService;
import com.planning.user.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamController {
    @NonNull
    private TeamService teamService;

    @PostMapping("")
    public String createTeamPlanner(@RequestBody CreateTeamPlannerDto requestDto) {
        User user = new User();
        return this.teamService.createTeamPlanner(requestDto, user);
    }

    @GetMapping("plan/{teamId}")
    public List<TeamPlanDto> getAllTeamPlan(@PathVariable Long teamId) {
        return this.teamService.getAllTeamPlan(teamId);
    }
}
