package com.planning.plan.repository;

import com.planning.plan.entity.MyPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MyPlanRepository extends JpaRepository<MyPlan,Long> {
}