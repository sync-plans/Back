package com.planning.plan.controller;

import com.planning.plan.dto.TeamPlanCreateDto;
import com.planning.plan.dto.TeamPlanDto;
import com.planning.plan.service.TeamPlanService;
import com.planning.user.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/team-plan")
@RequiredArgsConstructor
public class TeamPlanController {
    @NonNull
    private TeamPlanService teamPlanService;

    @PostMapping("/{teamId}")
    public ResponseEntity<TeamPlanDto> createTeamPlan(@PathVariable Long teamId, @RequestBody TeamPlanCreateDto requestDto) {
        User user = new User();
        return this.teamPlanService.createTeamPlan(teamId, requestDto, user);
    }

    @GetMapping("/{planId}")
    public TeamPlanDto getTeamPlan(@PathVariable Long planId) {
        return this.teamPlanService.getTeamPlan(planId);
    }

    @PutMapping("/{planId}")
    public TeamPlanDto putTeamPlan(@PathVariable Long planId, @RequestBody TeamPlanCreateDto requestDto) {
        return this.teamPlanService.putTeamPlan(planId, requestDto);
    }

    @DeleteMapping("/{planId}")
    public Long deleteTeamPlan(@PathVariable Long planId) {
        return this.teamPlanService.deleteTeamPlan(planId);
    }
}
