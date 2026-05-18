package com.example.umc10th.domain.mission.repository;


import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    // 특정 지역의 도전 가능 미션
    @Query("SELECT m FROM Mission m JOIN FETCH m.restaurant r JOIN FETCH r.region rg " +
            "WHERE rg.id = :regionId AND m.status = :status AND m.id > :cursor " +
            "ORDER BY m.id ASC")
    List<Mission> findAvailableMissions(@Param("regionId") Long regionId, @Param("status") MissionStatus status, @Param("cursor") Long cursor, Pageable pageable);
}