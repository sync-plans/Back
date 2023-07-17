package com.planning.plan.repository;

import com.planning.plan.entity.MyPlan;
import com.planning.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MyPlanRepository extends JpaRepository<MyPlan,Long> {
    List<MyPlan> findAllByUser(User user);
}