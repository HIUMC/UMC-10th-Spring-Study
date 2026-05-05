package com.example.umc10th.domain.membermission.repository;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.usermission.entity.UserMission;
import com.example.umc10th.domain.usermission.enums.UserMissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    Page<UserMission> findByMemberIdAndStatus(
            Long memberId,
            UserMissionStatus status,
            Pageable pageable
    );
}
