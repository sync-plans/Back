package com.planning.entity;

import com.planning.dto.PlanRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table
@NoArgsConstructor
public class MyPlan extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;
    @Column(nullable = false)
    private Integer priority;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public MyPlan(PlanRequestDto requestDto) {
        this.priority = requestDto.getPriority();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
//        this.user = user;
    }


    public void update(PlanRequestDto requestDto){
        this.priority = requestDto.getPriority();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
