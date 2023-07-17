package com.planning.plan.controller;

import com.planning.plan.dto.TeamPlanCreateDto;
import com.planning.plan.dto.TeamPlanDto;
import com.planning.plan.service.TeamPlanService;
import com.planning.security.UserDetailsImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team-plan")
@RequiredArgsConstructor
@Slf4j(topic = "TeamPlan controller")
public class TeamPlanController {
    @NonNull
    private TeamPlanService teamPlanService;

    @PostMapping("/{teamId}")
    public ResponseEntity<TeamPlanDto> createTeamPlan(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long teamId, @RequestBody TeamPlanCreateDto requestDto) {
        return this.teamPlanService.createTeamPlan(teamId, requestDto, userDetails.getUser());
    }

    @GetMapping("/{planId}")
    public TeamPlanDto getTeamPlan(@PathVariable Long planId) {
        return this.teamPlanService.getTeamPlan(planId);
    }

    @PutMapping("/{planId}")
    public TeamPlanDto putTeamPlan(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long planId, @RequestBody TeamPlanCreateDto requestDto) {
        return this.teamPlanService.putTeamPlan(planId, requestDto, userDetails.getUser());
    }

    @DeleteMapping("/{planId}")
    public Long deleteTeamPlan(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long planId) {
        return this.teamPlanService.deleteTeamPlan(planId, userDetails.getUser());
    }
}
