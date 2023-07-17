package com.planning.plan.entity;

import com.planning.plan.repository.TeamPlanRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;


@SpringBootTest
class TeamPlanTest {
    private TeamPlanRepository teamPlanRepository;

    @Autowired
    public TeamPlanTest(TeamPlanRepository teamPlanRepository) {
        this.teamPlanRepository = teamPlanRepository;
    }

    @Test
    @DisplayName("Datetime 입출력 test")
    @Transactional
    void dateTimeTest() {
        TeamPlan teamPlan = new TeamPlan();
        LocalDateTime of = LocalDateTime.of(2023, 7, 14, 22, 10, 5);
        teamPlan.setStartDate(of);
        teamPlan.setEndDate(of);
        TeamPlan save = teamPlanRepository.save(teamPlan);

        Optional<TeamPlan> Id = teamPlanRepository.findById(save.getId());
        System.out.println(Id.get().getStartDate());
    }

    @Test
    @DisplayName("Datetime 입출력 test2")
    @Transactional
    void dateTimeTest2() {
        TeamPlan teamPlan = new TeamPlan();
        Instant instant = Instant.ofEpochMilli(86400000);
        teamPlan.setStartDate(LocalDateTime.ofInstant(instant, ZoneOffset.UTC));
        teamPlan.setEndDate(LocalDateTime.ofInstant(instant, ZoneOffset.UTC));
        TeamPlan save = teamPlanRepository.save(teamPlan);

        Optional<TeamPlan> Id = teamPlanRepository.findById(save.getId());
        System.out.println(Id.get().getStartDate());
    }
}