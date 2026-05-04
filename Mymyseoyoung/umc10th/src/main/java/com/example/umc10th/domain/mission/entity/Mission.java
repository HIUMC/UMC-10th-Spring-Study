package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.common.base.BaseEntity;
import com.example.umc10th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="mission_id")
    private Long id;

    //제목
    @Column(name="title",nullable=false,length = 50)
    private String title;

    //설명
    @Column(name="description",nullable=false)
    private String description;

    //목표 금액
    @Column(name="goal_amount",nullable=false)
    private Integer goalAmount;

    //보상
    @Column(name="reward_points",nullable=false)
    private Integer rewardPoints;

    //미션 기한
    @Column(name="deadLine",nullable=false)
    private LocalDateTime deadLine;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;





}
