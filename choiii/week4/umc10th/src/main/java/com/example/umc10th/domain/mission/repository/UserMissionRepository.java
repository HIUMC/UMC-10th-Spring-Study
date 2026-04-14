package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.UserMission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    List<UserMission> findAllByUserId(Long userId);
}
