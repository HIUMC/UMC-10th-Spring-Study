package com.example.umc10th.domain.membermission.entity;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.membermission.enums.MemberMissionStatus;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.global.apiPayload.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_mission")
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MemberMissionStatus status = MemberMissionStatus.IN_PROGRESS;

    @Column(name = "due_date")
    private LocalDateTime dueDate;
}
