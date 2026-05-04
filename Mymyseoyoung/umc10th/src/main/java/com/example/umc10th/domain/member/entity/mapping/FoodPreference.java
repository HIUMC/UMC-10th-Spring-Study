package com.example.umc10th.domain.member.entity.mapping;


import com.example.umc10th.domain.common.base.BaseEntity;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="food_preference")
public class FoodPreference {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="food_preference_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;



}
