package com.example.umc10th.domain.membermission.repository;

import com.example.umc10th.domain.membermission.entity.MemberMission;
import com.example.umc10th.domain.membermission.enums.MemberMissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Page<MemberMission> findByMemberIdAndStatus(
            @Param("memberId") Long memberId,
            @Param("status") MemberMissionStatus status,
            Pageable pageable
    );

    Integer countByMemberId(Long memberId);

    Integer countByMemberIdAndStatus(Long memberId, MemberMissionStatus status);

    @Query("""
        select mm
        from MemberMission mm
        join fetch mm.mission m
        join fetch m.store s
        where mm.member.id = :memberId
            and mm.status = :status
            and s.address = :address
        """)
    Page<MemberMission> findByMemberIdAndStatusAndAddress(
            @Param("memberId") Long memberId,
            @Param("status") MemberMissionStatus status,
            @Param("address") String address,
            Pageable pageable
    );
}
