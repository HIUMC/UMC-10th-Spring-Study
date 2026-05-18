package com.example.demo.domain.mission.entity.mapping;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.mission.entity.Mission;
import jakarta.persistence.*;  // ← 이걸로만
import lombok.*;

// org.springframework.data.annotation.Id import 없어야 함

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member_mission")
public class MemberMission {

    @Id  // jakarta.persistence.Id 여야 함
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_complete", nullable = false)
    @Builder.Default
    private Boolean isComplete = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;
}