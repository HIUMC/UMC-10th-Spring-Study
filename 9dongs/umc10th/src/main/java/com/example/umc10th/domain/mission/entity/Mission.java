package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.restaurant.entity.Restaurant;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer reward;

    @Enumerated(EnumType.STRING)
    @Column(name = "mission_status", nullable = false)
    private MissionStatus status;

    @Column(nullable = false)
    private java.time.LocalDateTime deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}