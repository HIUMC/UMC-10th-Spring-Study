package com.example.umc10th.domain.member.entity;


import com.example.umc10th.domain.member.enums.FoodCategory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="food_id")
    private Long id;

    private FoodCategory foodCategory;


}
