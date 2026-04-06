package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.common.enums.MissionType;
import com.example.umc10th.domain.store.entity.Store;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "mission")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Mission {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(name = "mission_name", nullable = false)
    private String missionName;

    @Column(name = "reward_point")
    private Integer rewardPoint;

    @Column(name = "due_days")
    private LocalDate dueDays;

    @Enumerated(EnumType.STRING)
    @Column(name = "mission_type")
    private MissionType missionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
