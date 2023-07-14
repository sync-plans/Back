package com.planning.service;

import com.planning.dto.PlanRequestDto;
import com.planning.dto.PlanResponseDto;
import com.planning.entity.MyPlan;
import com.planning.repository.MyPlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPlanService {

    private final MyPlanRepository myPlanRepository;

    public List<PlanResponseDto> getPlans() {
        return myPlanRepository.findAll()
                .stream().map(PlanResponseDto::new).toList();
    }

    public PlanResponseDto createPlan(PlanRequestDto requestDto) {
        MyPlan myPlan = new MyPlan(requestDto);
        MyPlan savePlan = myPlanRepository.save(myPlan);
        return new PlanResponseDto(savePlan);
    }

    public PlanResponseDto getPlan(Long planId) {
        MyPlan myPlan = findPlan(planId);
        return new PlanResponseDto(myPlan);
    }

    @Transactional
    public PlanResponseDto updatePlan(Long planId, PlanRequestDto requestDto) {
        MyPlan myPlan = findPlan(planId);
        // role 확인 구현

        myPlan.update(requestDto);
        return new PlanResponseDto(myPlan);
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
