package com.example.umt10th.domain.mission.repository;

import com.example.umt10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m JOIN m.store s WHERE s.location.id = :locateId AND m.id > :cursor ORDER BY m.id ASC")
    List<Mission> findAllByLocateId(Long locateId, Long cursor, Pageable pageable);
}
