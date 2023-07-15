package com.planning.plan.service;

import com.planning.plan.dto.CreateTeamPlanDto;
import com.planning.plan.dto.TeamPlanDto;
import com.planning.plan.entity.Team;
import com.planning.plan.entity.TeamPlan;
import com.planning.plan.repository.TeamPlanRepository;
import com.planning.plan.repository.TeamRepository;
import com.planning.plan.repository.TeamUserRepository;
import com.planning.user.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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

    public TeamPlanDto createTeamPlan(Long teamId, CreateTeamPlanDto requestDto, User user) {
        TeamPlan teamPlan = new TeamPlan();
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 team 입니다."));
        teamPlan.update(requestDto);
        teamPlan.setTeam(team);
        teamPlan.setUser(user);
        TeamPlan saveTeamPlan = teamPlanRepository.save(teamPlan);
        return new TeamPlanDto(saveTeamPlan);
    }

    public TeamPlanDto getTeamPlan(Long planId) {
        TeamPlan teamPlan = this.teamPlanRepository.findById(planId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 team plan 입니다."));
        return new TeamPlanDto(teamPlan);
    }

    @Transactional
    public TeamPlanDto putTeamPlan(Long planId, CreateTeamPlanDto requestDto) {
        TeamPlan teamPlan = this.teamPlanRepository.findById(planId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 team plan 입니다."));
        teamPlan.update(requestDto);
        return new TeamPlanDto(teamPlan);
    }

    public Long deleteTeamPlan(Long planId) {
        TeamPlan teamPlan = this.teamPlanRepository.findById(planId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 team plan 입니다."));
        teamPlanRepository.delete(teamPlan);
        return planId;
    }
}
