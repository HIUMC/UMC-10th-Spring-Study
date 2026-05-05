package umc10th.assignment.mission.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import umc10th.assignment.mission.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
        select m
        from Mission m
        join fetch m.store s
        join fetch s.region r
        where r.locationId = :regionId
          and m.deletedAt is null
        """)
    Page<Mission> findAvailableMissionsByRegion(
            @Param("regionId") Long regionId,
            Pageable pageable
    );
}
