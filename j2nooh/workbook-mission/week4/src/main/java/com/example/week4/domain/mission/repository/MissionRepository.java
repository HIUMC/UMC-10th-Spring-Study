package com.example.week4.domain.mission.repository;

import com.example.week4.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 특정 가게의 미션 목록 조회
    @Query(
            value = """
                    select m
                    from Mission m
                    join fetch m.store s
                    where s.id = :storeId
                    order by m.id desc
                    """,
            countQuery = """
                    select count(m)
                    from Mission m
                    where m.store.id = :storeId
                    """
    )
    Page<Mission> findStoreMissions(
            @Param("storeId") Long storeId,
            Pageable pageable
    );
}