package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.common.enums.MissionStatus;
import com.example.umc10th.domain.mission.entity.UserMission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    @Query("""
    SELECT um
    FROM UserMission um
    JOIN FETCH um.mission m
    WHERE um.user.id = :userId
    """
        )
    Page<UserMission> findAllByUserId(
            @Param("userId") Long userId,
            Pageable pageable);

    @Query("""
    SELECT um
    FROM UserMission um
    JOIN FETCH um.mission m
    WHERE um.user.id = :userId
          AND um.status = :status
    """)
    Page<UserMission> findAllByUserIdAndStatus(
            @Param("userId") Long userId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );
}


