package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.common.enums.MissionStatus;
import com.example.umc10th.domain.mission.entity.UserMission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    Page<UserMission> findAllByUserId(Long userId, Pageable pageable);
    Page<UserMission> findAllByUserIdAndStatus(
            Long userId,
            MissionStatus status,
            Pageable pageable
    );
}


