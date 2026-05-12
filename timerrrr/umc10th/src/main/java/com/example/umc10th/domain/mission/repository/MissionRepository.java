package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    //회원이 선택한 주소 기준 도전 가능 미션 목록
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
            ") ")
    Page<Mission> findAvailableMissions(
            @Param("memberId") Long memberId,
            @Param("memberAddressId") Long memberAddressId,
            @Param("today") LocalDate today,
            Pageable pageable
    );
}

