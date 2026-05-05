package com.example.umt10th.domain.member.entity;

import com.example.umt10th.domain.member.enums.FoodType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private FoodType foodType = FoodType.NONE;
}
