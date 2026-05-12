package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    @Query("""
            SELECT mm
            FROM MemberMission mm
            JOIN FETCH mm.mission m
            JOIN FETCH m.store s
            WHERE mm.member.id = :memberId
              AND mm.isCompleted = :isCompleted
              AND mm.id > :cursor
            ORDER BY mm.id ASC
            """)
    List<MemberMission> findMemberMissionsByStatus(
            @Param("memberId") Long memberId,
            @Param("isCompleted") Boolean isCompleted,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("""
            SELECT mm
            FROM MemberMission mm
            WHERE mm.member.id = :memberId
              AND mm.mission.id = :missionId
            """)
    Optional<MemberMission> findByMemberIdAndMissionId(
            @Param("memberId") Long memberId,
            @Param("missionId") Long missionId
    );

}
