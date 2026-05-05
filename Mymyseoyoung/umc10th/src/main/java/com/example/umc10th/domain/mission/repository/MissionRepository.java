package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m " +
            "JOIN m.store s " +
            "WHERE s.region.id = :regionId " +
            "AND m.id NOT IN (" +
            "SELECT um.mission.id FROM UserMission um " +
            "WHERE um.member.id = :memberId " +
            "AND um.status IN ('CHALLENGING', 'COMPLETE', 'EXPIRED')" +
            ")")

    Page<Mission> findAvailableMissionsByRegion(
            @Param("regionId") Long regionId,
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}
