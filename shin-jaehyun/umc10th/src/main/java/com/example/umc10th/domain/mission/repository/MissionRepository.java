package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("""
        SELECT m
        FROM Mission m
        JOIN m.store s
        WHERE s.latitude BETWEEN :minLat AND :maxLat
        AND s.longitude BETWEEN :minLng AND :maxLng
    """)
    List<Mission> findMissionsByRegion(@Param("minLat") Double minLat, @Param("maxLat") Double maxLat, @Param("minLng") Double minLng, @Param("maxLng") Double maxLng);

    @Query(value = """
            SELECT COUNT(*)
            FROM member_mission
            WHERE member_mission.member_id = :memberId AND member_mission.status = 'COMPLETED'
           """, nativeQuery = true)
    Long countCompletedMission(@Param("memberId") Long memberId);

    @Query(value = """
    SELECT store.name, store.food_category_id, mission.content, mission.reward, mission.deadline
    FROM mission
    JOIN store ON mission.store_id = store.store_id
    JOIN region ON region.region_id = store.region_id
    LEFT JOIN member_mission ON member_mission.mission_id = mission.mission_id AND member_mission.member_id = :memberId
    WHERE region.name = '안암동'
    AND (member_mission.member_mission_id IS NULL OR member_mission.status != 'COMPLETED')
    AND (mission.created_at < :lastCreatedAt
    OR (mission.created_at = :lastCreatedAt AND mission.mission_id < :lastMissionId)
    )
    ORDER BY mission.created_at DESC, mission.mission_id DESC
    LIMIT 15
    """, nativeQuery = true)
    List<MissionResDTO.getMissions> findMyMissions(@Param("memberId") Long memberId, @Param("lastCreatedAt") LocalDateTime lastCreatedAt, @Param("lastMissionId") Long lastMissionId);

    @Query(value = """
        SELECT s.name, s.food_category_id, m.content, m.reward, m.deadline
        FROM member_mission mm
        JOIN mission m ON m.id = mm.mission_id
        JOIN store s ON m.store_id = s.id
        WHERE mm.member_id = :memberId
        AND mm.status = :status
        """, nativeQuery = true)
    Page<MissionResDTO.getMissions> findMyMissionsByStatus(@Param("memberId") Long memberId, @Param("status") Status status, Pageable pageable);
}
