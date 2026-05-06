package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    /**
     * 내 미션 목록 조회
     * - status: "CHALLENGING" (도전 중) / "COMPLETE" (완료)
     * - 첫 페이지 호출 시 lastDeadline, lastMissionId는 null
     * - 정렬: mission.deadline ASC, mission.id DESC
     */
    @Query("SELECT mm FROM MemberMission mm " +
            "WHERE mm.member.id = :memberId " +
            "AND (:status = 'CHALLENGING' AND mm.IsMissionComplete = false " +
            "OR :status = 'COMPLETE' AND mm.IsMissionComplete = true) " +
            "AND ( :lastDeadline IS NULL " +
            "      OR mm.mission.deadline > :lastDeadline " +
            "      OR (mm.mission.deadline = :lastDeadline AND mm.mission.id < :lastMissionId) ) " +
            "ORDER BY mm.mission.deadline ASC, mm.mission.id DESC")
    List<MemberMission> findMyMissions(
            @Param("memberId") Long memberId,
            @Param("status") String status,
            @Param("lastDeadline") LocalDate lastDeadline,
            @Param("lastMissionId") Long lastMissionId,
            Pageable pageable
    );

    // 특정 미션 조회
    @Query("SELECT mm FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.mission.id = :missionId")
    Optional<MemberMission> findByMemberIdAndMissionId(
            @Param("memberId") Long memberId,
            @Param("missionId") Long missionId
    );
}