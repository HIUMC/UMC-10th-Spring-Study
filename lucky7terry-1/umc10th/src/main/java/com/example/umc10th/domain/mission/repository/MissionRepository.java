package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("""
            SELECT m
            FROM Mission m
            JOIN FETCH m.store s
            JOIN FETCH s.region r
            JOIN FETCH s.category c
            WHERE r.id = :regionId
              AND m.id > :cursor
              AND m.deadline >= :today
              AND NOT EXISTS (
                    SELECT mm.id
                    FROM MemberMission mm
                    WHERE mm.member.id = :memberId
                      AND mm.mission.id = m.id
              )
            ORDER BY m.id ASC
            """)

    List<Mission> findAvailableMissionsByRegion(
            @Param("regionId") Long regionId,
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            @Param("today") LocalDate today,
            Pageable pageable
    );

    @Query("""
            SELECT COUNT(mm)
            FROM MemberMission mm
            WHERE mm.member.id = :memberId
              AND mm.isCompleted = true
            """)
    long countCompletedMissionsByMemberId(@Param("memberId") Long memberId);


}
