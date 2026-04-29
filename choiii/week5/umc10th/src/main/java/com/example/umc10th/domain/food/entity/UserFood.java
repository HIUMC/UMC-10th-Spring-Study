package com.example.umc10th.domain.food.entity;

import com.example.umc10th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_food")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserFood {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_food_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
