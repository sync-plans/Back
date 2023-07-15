package com.planning.plan.repository;

import com.planning.plan.entity.TeamUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamUserRepository extends JpaRepository<TeamUser, Long> {
}
