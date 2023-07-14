package com.planning.repository;

import com.planning.entity.MyPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MyPlanRepository extends JpaRepository<MyPlan,Long> {
}