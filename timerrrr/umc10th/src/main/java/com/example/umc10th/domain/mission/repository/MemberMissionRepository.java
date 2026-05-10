package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Page<MemberMission> findAllByMember_IdAndMissionComplete(Long memberId, Boolean isMissionComplete, Pageable pageable);

    // 특정 미션 조회
    @Query("SELECT mm FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.mission.id = :missionId")
    Optional<MemberMission> findByMemberIdAndMissionId(
            @Param("memberId") Long memberId,
            @Param("missionId") Long missionId
    );
}