package com.planning.plan.repository;

import com.planning.plan.entity.Team;
import com.planning.plan.entity.TeamUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamUserRepository extends JpaRepository<TeamUser, Long> {
    List<TeamUser> findAllByTeam(Team team);
}
