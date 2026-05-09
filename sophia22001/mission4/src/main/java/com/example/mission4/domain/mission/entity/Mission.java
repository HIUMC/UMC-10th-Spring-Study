package com.example.mission4.domain.mission.entity;

import com.example.mission4.domain.mission.entity.mapping.MemberMission;
import com.example.mission4.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mission")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Builder.Default
    private Long point = 0L; // 기본 0포인트

    @Column(name = "mission_condition", nullable = false)
    @Builder.Default
    private String condition = "미지정";

    @Column(nullable = false)
    @Builder.Default
    private LocalDate deadline = LocalDate.now().plusDays(7); // 기본 일주일 기한

    @OneToMany(mappedBy = "mission")
    private List<MemberMission> memberMissionList = new ArrayList<>();

    // 단방향 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
