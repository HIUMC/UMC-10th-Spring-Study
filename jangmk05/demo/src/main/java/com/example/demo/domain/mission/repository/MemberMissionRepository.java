package com.example.demo.domain.mission.repository;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.mission.entity.mapping.MemberMission;
import com.example.demo.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Page<MemberMission> findAllByMemberAndMissionStatus(Member member, MissionStatus status, Pageable pageable);
}