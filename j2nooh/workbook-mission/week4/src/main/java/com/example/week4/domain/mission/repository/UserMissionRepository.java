package com.example.week4.domain.mission.repository;

import com.example.week4.domain.mission.entity.mapping.UserMission;
import com.example.week4.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 유저의 진행 중/완료 미션 목록 조회
    @Query(
            value = """
                    select um
                    from UserMission um
                    join fetch um.mission m
                    join fetch m.store s
                    where um.user.id = :userId
                      and um.missionStatus = :missionStatus
                    order by um.assignedAt desc
                    """,
            countQuery = """
                    select count(um)
                    from UserMission um
                    where um.user.id = :userId
                      and um.missionStatus = :missionStatus
                    """
    )
    Page<UserMission> findUserMissions(
            @Param("userId") Long userId,
            @Param("missionStatus") MissionStatus missionStatus,
            Pageable pageable
    );

    // 이미 해당 미션에 도전했는지 확인
    @Query("""
            select count(um) > 0
            from UserMission um
            where um.user.id = :userId
              and um.mission.id = :missionId
            """)
    boolean existsByUserIdAndMissionId(
            @Param("userId") Long userId,
            @Param("missionId") Long missionId
    );
}