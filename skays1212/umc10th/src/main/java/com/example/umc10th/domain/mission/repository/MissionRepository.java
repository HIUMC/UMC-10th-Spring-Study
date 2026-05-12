package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 메서드 이름을 파싱해서 자동으로 JPQL 생성
    Page<Mission> findAllByStoreId(Long storeId, Pageable pageable);
}
