package com.planning.plan.repository;

import com.planning.plan.entity.Team;
import com.planning.plan.entity.TeamPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamPlanRepository extends JpaRepository<TeamPlan, Long> {
    List<TeamPlan> findAllByTeam(Team team);
}
