package com.planning.plan.service;

import com.planning.plan.dto.TeamInviteDto;
import com.planning.plan.dto.TeamPlannerCreateDto;
import com.planning.plan.dto.TeamPlanDto;
import com.planning.plan.dto.TeamUserDto;
import com.planning.plan.entity.Team;
import com.planning.plan.entity.TeamUser;
import com.planning.plan.repository.TeamPlanRepository;
import com.planning.plan.repository.TeamRepository;
import com.planning.plan.repository.TeamUserRepository;
import com.planning.user.entity.User;
import com.planning.user.repository.UserRepository;
import jakarta.validation.ValidationException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
    @NonNull
    private UserRepository userRepository;

    public List<TeamPlanDto> getAllTeamPlan(Long teamId) {
        Team team = this.findTeam(teamId);
        return teamPlanRepository.findAllByTeam(team).stream().map(TeamPlanDto::new).collect(Collectors.toList());
    }

    @Transactional
    public TeamUserDto createTeamPlanner(TeamPlannerCreateDto requestDto, User user) {
        Team saveTeam = teamRepository.save(new Team(requestDto));
        TeamUser saveTeamUser = teamUserRepository.save(new TeamUser(saveTeam, user));
        return new TeamUserDto(saveTeamUser);
    }

    public TeamUserDto inviteToTeam(Long teamId, TeamInviteDto requestDto, User user) {
        /*
        - 초대받는 user 가 존재해야 한다.
        - 초대하려는 user 가 팀에 속하고 권한이 있어야 한다. (권한은 나중에)
        - 초대받는 user 가 이미 같은 팀에 속해 있지 않아야 한다.
        */
        Optional<User> optionalUser = userRepository.findByUsername(requestDto.getUsername());
        if (optionalUser.isEmpty()) throw new ValidationException("잘못된 정보입니다.");
        User invitedUser = optionalUser.get();

        Team team = this.findTeam(teamId);
        List<TeamUser> allMember = teamUserRepository.findAllByTeam(team);
        if (!checkUserOnTeam(allMember, user)) throw new ValidationException("잘못된 접근입니다1.");
        if (checkUserOnTeam(allMember, invitedUser)) throw new ValidationException("잘못된 접근입니다2.");

        TeamUser saveTeamUser = teamUserRepository.save(new TeamUser(team, invitedUser));
        return new TeamUserDto(saveTeamUser);
    }

    public Team findTeam(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 team 입니다."));
    }

    public boolean checkUserOnTeam(List<TeamUser> allMember, User user) {
        for (TeamUser member : allMember) {
            if (user.equals(member.getUser())) return true;
        }
        return false;
    }
}
