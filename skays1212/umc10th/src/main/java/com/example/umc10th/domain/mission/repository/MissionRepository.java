package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 특정 지역에서 해당 회원이 아직 도전하지 않은 미션 목록 조회 (페이징)
    // FETCH JOIN으로 store, location을 한 번에 조회해 N+1 문제 방지
    // 서브쿼리로 이미 사용자가 도전한 미션(MemberMission에 존재하는 mission_id)을 제외
    @Query("SELECT m FROM Mission m JOIN FETCH m.store s JOIN FETCH s.location l WHERE l.name = :address AND m.id NOT IN (SELECT mm.mission.id FROM MemberMission mm WHERE mm.member.id = :memberId)")
    Page<Mission> findAvailableMissions(@Param("address") Address address, @Param("memberId") Long memberId, Pageable pageable);
}
