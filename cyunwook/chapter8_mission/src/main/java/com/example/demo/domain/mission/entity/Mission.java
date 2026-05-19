package com.example.demo.domain.mission.entity;

import com.example.demo.domain.mission.entity.mapping.MemberMission;
import com.example.demo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String conditional;

    @Column(nullable = false)
    private Integer point;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissions = new ArrayList<>();
}
