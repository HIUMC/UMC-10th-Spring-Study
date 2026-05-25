package com.example.demo.domain.mission.repository;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.enums.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m " +
            "WHERE m.store.region = :address " +
            "AND m.id NOT IN (" +
            "   SELECT mm.mission.id FROM MemberMission mm WHERE mm.member = :member" +
            ")")
    Page<Mission> findAvailableMissionsByRegion(
            @Param("member") Member member,
            @Param("address") Address address,
            Pageable pageable
    );

    Slice<Mission> findMissionsByStore_IdAndIdLessThanOrderByIdDesc(Long storeId, long idCursor, PageRequest pageRequest);

    Slice<Mission> findMissionByStore_IdOrderByIdDesc(Long storeId, PageRequest pageRequest);
}