package com.planning.plan.service;

import com.planning.plan.dto.TeamPlanCreateDto;
import com.planning.plan.dto.TeamPlanDto;
import com.planning.plan.entity.Team;
import com.planning.plan.entity.TeamPlan;
import com.planning.plan.repository.TeamPlanRepository;
import com.planning.plan.repository.TeamRepository;
import com.planning.plan.repository.TeamUserRepository;
import com.planning.user.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamPlanService {

    @NonNull
    private TeamPlanRepository teamPlanRepository;
    @NonNull
    private TeamRepository teamRepository;
    @NonNull
    private TeamUserRepository teamUserRepository;
    @NonNull
    private TeamService teamService;

    public ResponseEntity<TeamPlanDto> createTeamPlan(Long teamId, TeamPlanCreateDto requestDto, User user) {
        Team team = teamService.findTeam(teamId);
        TeamPlan saveTeamPlan = teamPlanRepository.save(new TeamPlan(requestDto, team, user));
        return ResponseEntity.status(HttpStatus.CREATED).body(new TeamPlanDto(saveTeamPlan));
    }

    public TeamPlanDto getTeamPlan(Long planId) {
        TeamPlan teamPlan = findTeamPlan(planId);
        return new TeamPlanDto(teamPlan);
    }

    @Transactional
    public TeamPlanDto putTeamPlan(Long planId, TeamPlanCreateDto requestDto, User user) {
        TeamPlan teamPlan = userValidateReturnTeamPlan(planId, user);
        teamPlan.update(requestDto);
        return new TeamPlanDto(teamPlan);
    }

    public Long deleteTeamPlan(Long planId, User user) {
        TeamPlan teamPlan = userValidateReturnTeamPlan(planId, user);
        teamPlanRepository.delete(teamPlan);
        return planId;
    }

    public TeamPlan findTeamPlan(Long planId) {
        return this.teamPlanRepository.findById(planId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 team plan 입니다."));
    }

    public TeamPlan userValidateReturnTeamPlan(Long teamPlanId, User user) {
        TeamPlan teamPlan = this.findTeamPlan(teamPlanId);
        if (!user.equals(teamPlan.getUser())) throw new IllegalArgumentException("잘못된 User의 접근입니다.");
        return teamPlan;
    }
}
