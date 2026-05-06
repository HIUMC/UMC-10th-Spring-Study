package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    /**
     * 회원이 선택한 주소(동네) 기준 도전 가능 미션 목록 (No-Offset 커서 페이징)
     *
     * - 회원이 보낸 memberAddressId의 동네(townName)와
     *   가게 주소(storeAddress)가 일치하는 미션만 조회
     * - 마감일이 지나지 않은(today 이상) 미션
     * - 회원이 아직 도전하지 않은(MemberMission에 없는) 미션
     * - 정렬: deadline ASC, mission.id DESC
     * - 첫 페이지 호출 시 cursorDeadline, cursorMissionId는 null
     */
    @Query("SELECT m FROM Mission m " +
            "JOIN m.store s " +
            "WHERE EXISTS ( " +
            "    SELECT 1 FROM MemberAddress ma " +
            "    WHERE ma.id = :memberAddressId " +
            "      AND s.storeAddress LIKE CONCAT('%', ma.townName, '%') " +
            ") " +
            "AND m.deadline >= :today " +
            "AND NOT EXISTS ( " +
            "    SELECT 1 FROM MemberMission mm " +
            "    WHERE mm.mission = m AND mm.member.id = :memberId " +
            ") " +
            "AND ( :cursorDeadline IS NULL " +
            "      OR m.deadline > :cursorDeadline " +
            "      OR (m.deadline = :cursorDeadline AND m.id < :cursorMissionId) ) " +
            "ORDER BY m.deadline ASC, m.id DESC")
    List<Mission> findAvailableMissions(
            @Param("memberId") Long memberId,
            @Param("memberAddressId") Long memberAddressId,
            @Param("today") LocalDate today,
            @Param("cursorDeadline") LocalDate cursorDeadline,
            @Param("cursorMissionId") Long cursorMissionId,
            Pageable pageable
    );
}

