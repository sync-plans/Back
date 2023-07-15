package com.planning.plan.service;

import com.planning.plan.dto.MyPlanRequestDto;
import com.planning.plan.dto.MyPlanDto;
import com.planning.plan.entity.MyPlan;
import com.planning.plan.repository.MyPlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPlanService {

    private final MyPlanRepository myPlanRepository;

    public List<MyPlanDto> getPlans() {
        return myPlanRepository.findAll()
                .stream().map(MyPlanDto::new).toList();
    }

    public MyPlanDto createPlan(MyPlanRequestDto requestDto) {
        MyPlan myPlan = new MyPlan(requestDto);
        MyPlan savePlan = myPlanRepository.save(myPlan);
        return new MyPlanDto(savePlan);
    }

    public MyPlanDto getPlan(Long planId) {
        MyPlan myPlan = findPlan(planId);
        return new MyPlanDto(myPlan);
    }

    @Transactional
    public MyPlanDto updatePlan(Long planId, MyPlanRequestDto requestDto) {
        MyPlan myPlan = findPlan(planId);
        // role 확인 구현

        myPlan.update(requestDto);
        return new MyPlanDto(myPlan);
    }

    public void deletePlan(Long planId) {
        MyPlan myPlan = findPlan(planId);
        // role 확인 구현

        myPlanRepository.delete(myPlan);
    }

    private MyPlan findPlan(Long planId) {
        return myPlanRepository.findById(planId).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다."));
    }

}
