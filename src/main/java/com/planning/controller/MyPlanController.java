package com.planning.controller;

import com.planning.dto.ApiResponseDto;
import com.planning.dto.PlanRequestDto;
import com.planning.dto.PlanResponseDto;
import com.planning.service.MyPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyPlanController {

    private final MyPlanService planService;

    @GetMapping("/my-plan") // 플랜 전체 조회
    public List<PlanResponseDto> getPlans(){
        return planService.getPlans();
    }

    @PostMapping("/my-plan") // 플랜 등록
    public ResponseEntity<PlanResponseDto> createPlan(@RequestBody PlanRequestDto requestDto) {
        PlanResponseDto responseDto = planService.createPlan(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/my-plan/{planId}") // 플랜 id 조회
    public PlanResponseDto getPlan(@PathVariable Long planId){
        return planService.getPlan(planId);
    }

    @PutMapping("/my-plan/{planId}") // 플랜 수정
    public ResponseEntity<PlanResponseDto> updatePlan(@PathVariable Long planId, @RequestBody PlanRequestDto requestDto){
        PlanResponseDto responseDto = planService.updatePlan(planId, requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/my-plan/{planId}") // 플랜 삭제
    public ResponseEntity<ApiResponseDto> deletePlan(@PathVariable Long planId){
        planService.deletePlan(planId);
        return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(),"일정이 삭제되었습니다."));
    }

}