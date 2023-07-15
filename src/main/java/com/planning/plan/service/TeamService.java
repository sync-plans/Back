package com.planning.plan.service;

import com.planning.plan.dto.TeamPlannerCreateDto;
import com.planning.plan.dto.TeamPlanDto;
import com.planning.plan.entity.Team;
import com.planning.plan.entity.TeamUser;
import com.planning.plan.repository.TeamPlanRepository;
import com.planning.plan.repository.TeamRepository;
import com.planning.plan.repository.TeamUserRepository;
import com.planning.user.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {
    @NonNull
    private TeamPlanRepository teamPlanRepository;
    @NonNull
    private TeamRepository teamRepository;
    @NonNull
    private TeamUserRepository teamUserRepository;

    public List<TeamPlanDto> getAllTeamPlan(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 team 입니다."));
        return teamPlanRepository.findAllByTeam(team).stream().map(TeamPlanDto::new).collect(Collectors.toList());
    }

    @Transactional
    public String createTeamPlanner(TeamPlannerCreateDto requestDto, User user) {
        Team saveTeam = teamRepository.save(new Team(requestDto));
        teamUserRepository.save(new TeamUser(saveTeam, user));
        return "success";
    }
}
