package com.planning.plan.service;

import com.planning.plan.dto.ApiResponseDto;
import com.planning.plan.dto.MyPlanRequestDto;
import com.planning.plan.dto.MyPlanDto;
import com.planning.plan.entity.MyPlan;
import com.planning.plan.repository.MyPlanRepository;
import com.planning.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    public MyPlanDto createPlan(MyPlanRequestDto requestDto, User user) {
        MyPlan myPlan = new MyPlan(requestDto, user);
        MyPlan savePlan = myPlanRepository.save(myPlan);
        return new MyPlanDto(savePlan);
    }

    public MyPlanDto getPlan(Long planId) {
        MyPlan myPlan = findPlan(planId);
        return new MyPlanDto(myPlan);
    }

    @Transactional
    public MyPlanDto updatePlan(Long planId, MyPlanRequestDto requestDto, User user) {
        MyPlan myPlan = userValidateReturnMyPlan(planId, user);
        // role 확인 구현

        myPlan.update(requestDto);
        return new MyPlanDto(myPlan);
    }

    public ApiResponseDto deletePlan(Long planId, User user) {
        MyPlan myPlan = userValidateReturnMyPlan(planId, user);
        // role 확인 구현

        myPlanRepository.delete(myPlan);
        return new ApiResponseDto(HttpStatus.OK.value(), "일정이 삭제되었습니다.");
    }

    private MyPlan findPlan(Long planId) {
        return myPlanRepository.findById(planId).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다."));
    }

    private MyPlan userValidateReturnMyPlan(Long myPlanId, User user) {
        MyPlan myPlan = this.findPlan(myPlanId);
        if (!user.equals(myPlan.getUser())) throw new IllegalArgumentException("잘못된 User의 접근입니다.");
        return myPlan;
    }

}
