package com.example.demo.domain.mission.entity;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.mission.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complete_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "mission_id")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @Column(name = "completed_at")
    private Date completedAt;
}
