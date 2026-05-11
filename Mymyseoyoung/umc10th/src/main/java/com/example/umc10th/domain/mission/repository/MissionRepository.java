package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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

    // 연관관계가 있는 store 테이블 아이디를 where 절에 넣어서 쿼리를 날림
    Page<Mission> findAllByStore_Id(Long storeId, PageRequest pageRequest);


}
