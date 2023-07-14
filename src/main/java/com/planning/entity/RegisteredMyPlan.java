package com.planning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table
@NoArgsConstructor
public class RegisteredMyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "myplan_id")
    private MyPlan myPlan;

}
