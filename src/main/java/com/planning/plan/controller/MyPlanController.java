package com.planning.plan.controller;

import com.planning.plan.dto.ApiResponseDto;
import com.planning.plan.dto.MyPlanRequestDto;
import com.planning.plan.dto.MyPlanDto;
import com.planning.plan.service.MyPlanService;
import com.planning.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/my-plan")
@RequiredArgsConstructor
public class MyPlanController {

    private final MyPlanService planService;

    @GetMapping("") // 플랜 전체 조회
    public List<MyPlanDto> getPlans(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return planService.getPlans(userDetails.getUser());
    }

    @PostMapping("") // 플랜 등록
    public ResponseEntity<MyPlanDto> createPlan(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody MyPlanRequestDto requestDto) {
        MyPlanDto myPlanDto = planService.createPlan(requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(myPlanDto);
    }

    @GetMapping("/{planId}") // 플랜 id 조회
    public MyPlanDto getPlan(@PathVariable Long planId){
        return planService.getPlan(planId);
    }

    @PutMapping("/{planId}") // 플랜 수정
    public MyPlanDto updatePlan(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long planId, @RequestBody MyPlanRequestDto requestDto){
        return planService.updatePlan(planId, requestDto, userDetails.getUser());
    }

    @DeleteMapping("/{planId}") // 플랜 삭제
    public ApiResponseDto deletePlan(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long planId){
        return planService.deletePlan(planId, userDetails.getUser());
    }

}