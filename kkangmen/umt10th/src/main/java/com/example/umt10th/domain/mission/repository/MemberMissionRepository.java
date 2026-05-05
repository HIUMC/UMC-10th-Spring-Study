package com.example.umt10th.domain.mission.repository;

import com.example.umt10th.domain.mission.entity.Mission;
import com.example.umt10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("SELECT mm.mission FROM MemberMission mm WHERE mm.isComplete = :isCompleted AND mm.member.id = :memberId AND mm.id > :cursor ORDER BY mm.id ASC")
    List<Mission> findCompletedMissionByMemberId(Long memberId, Boolean isCompleted, Long cursor, Pageable pageable);
}
