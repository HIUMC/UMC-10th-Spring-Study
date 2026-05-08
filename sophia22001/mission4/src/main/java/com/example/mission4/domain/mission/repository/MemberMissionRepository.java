package com.example.mission4.domain.mission.repository;

import com.example.mission4.domain.mission.entity.Mission;
import com.example.mission4.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMissionRepository  extends JpaRepository<MemberMission, Long> {

    // 미션의 가게 이름을 가져오기 위해 N+1번의 추가 쿼리가 나갈 수 있다.
    // FETCH JOIN
    @Query("select mm from MemberMission mm join fetch mm.mission m join fetch m.store s where mm.member.id = :memberId and mm.isComplete = :isComplete")
    List<MemberMission> findAllByMemberIdAndIsComplete(@Param("memberId") Long memberId,
                                                       @Param("isComplete") Boolean isCompleted);
}
