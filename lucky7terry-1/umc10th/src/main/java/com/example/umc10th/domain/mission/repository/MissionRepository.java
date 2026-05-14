package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m " +
            "join m.store s " +
            "where s.region.id = :regionId " +
            "and m.id > :cursor " +
            "ORDER BY m.id ASC ")
    List<Mission> findAllByRegionId(Long regionId, Long cursor, Pageable pageable);


    Slice<Mission> findMissionByStore_IdAndIdLessThanOrderByIdDesc(Long storeId, long idCursor, long prevCursor);


    Slice<Mission> findMissionByStore_IdOrderByIdDesc(Long storeId, PageRequest pageRequest);


    @Query("select mm.mission " +
            "from MemberMission mm " +
            "where mm.member.id = :memberId " +
            "and mm.isCompleted = :status " +
            "and mm.id < :idCursor " +
            "order by mm.id desc ")
    Slice<Mission> findOngoingMissions(@NotNull(message = "로그인 후 이용해주세요.") Long memberId, boolean status, long idCursor, PageRequest pageRequest);

    @Query("select mm.mission " +
            "from MemberMission  mm " +
            "where mm.member.id = :memberId and mm.isCompleted = :status " +
            "order by mm.id desc ")
    Slice<Mission> findOngoingMissionsWithoutCursor(@NotNull(message = "로그인 후 이용해주세요.") Long memberId, boolean status, PageRequest pageRequest);


    @Query("""
            SELECT m
            FROM Mission m
            JOIN FETCH m.store s
            JOIN FETCH s.region r
            JOIN FETCH s.category c
            WHERE r.id = :regionId
              AND m.id > :cursor
              AND m.deadline >= :today
              AND NOT EXISTS (
                    SELECT mm.id
                    FROM MemberMission mm
                    WHERE mm.member.id = :memberId
                      AND mm.mission.id = m.id
              )
            ORDER BY m.id ASC
            """)
    List<Mission> findAvailableMissionsByRegion(
            @Param("regionId") Long regionId,
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            @Param("today") LocalDate today,
            Pageable pageable
    );


    @Query("""
            SELECT COUNT(mm)
            FROM MemberMission mm
            WHERE mm.member.id = :memberId
              AND mm.isCompleted = true
            """)
    long countCompletedMissionsByMemberId(@Param("memberId") Long memberId);
}
