package com.example.demo.domain.mission.repository;

import com.example.demo.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 홈화면 - 지역별 미션 목록
    @Query("SELECT m FROM Mission m " +
            "JOIN FETCH m.store s " +
            "WHERE s.location.id = :locationId " +
            "AND m.deletedAt IS NULL " +
            "ORDER BY m.deadline ASC")
    Page<Mission> findByLocationId(@Param("locationId") Long locationId, Pageable pageable);



    Page<Mission> findAllByStore_Id(Long storeId, Pageable pageable);
}
