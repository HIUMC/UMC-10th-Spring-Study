package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("SELECT mm FROM MemberMission mm " +
            "WHERE mm.member = :member AND mm.status = :status " +
            "ORDER BY mm.updatedAt DESC, mm.id DESC")
    Slice<MemberMission> findFirstPage(Member member, MissionStatus status, Pageable pageable);

    @Query("SELECT mm FROM MemberMission mm " +
            "WHERE mm.member = :member AND mm.status = :status " +
            "AND (mm.updatedAt < :updatedAt OR (mm.updatedAt = :updatedAt AND mm.id < :id)) " +
            "ORDER BY mm.updatedAt DESC, mm.id DESC")
    Slice<MemberMission> findNextPage(Member member, MissionStatus status, LocalDateTime updatedAt, Long id,
                                      Pageable pageable);

    Page<MemberMission> findAllByMemberAndStatusOrderByUpdatedAtDesc(Member member, MissionStatus status, Pageable pageable);
}
