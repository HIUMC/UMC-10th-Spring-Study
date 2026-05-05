package com.example.umc10th.domain.foodpreference.entity;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.global.apiPayload.entity.BaseEntity;
import com.example.umc10th.global.enums.Food;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "food_preference")
public class FoodPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "food")
    private Food food;
}
