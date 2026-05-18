package com.example.umt10th.domain.mission.repository;

import com.example.umt10th.domain.mission.entity.Mission;
import com.example.umt10th.domain.mission.entity.Store;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m JOIN m.store s WHERE s.location.id = :locateId AND m.id > :cursor ORDER BY m.id ASC")
    List<Mission> findAllByLocateId(Long locateId, Long cursor, Pageable pageable);

    List<Mission> store(Store store);

    Page<Mission> findAllByStore_Id(Long storeId, PageRequest request);

    Slice<Mission> findMissionByStore_IdAndIdLessThanOrderByIdDesc(Long storeId, long idCursor, PageRequest pageRequest);

    Slice<Mission> findMissionByStore_IdOrderByIdDesc(Long storeId, PageRequest pageRequest);

    @Query("select mm.mission from MemberMission mm where mm.member.id = :memberId and mm.isComplete = :status and mm.id < :idCursor order by mm.id desc ")
    Slice<Mission> findOngoingMissions(Long memberId, boolean status, long idCursor, PageRequest pageRequest);

    @Query("select mm.mission from MemberMission mm where mm.member.id = :memberId and mm.isComplete = :status order by mm.id desc")
    Slice<Mission> findOngoingMissionsWithoutCursor(Long memberId, boolean status,PageRequest pageRequest);
}
