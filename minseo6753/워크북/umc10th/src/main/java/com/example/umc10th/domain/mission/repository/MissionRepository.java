package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.restaurant.entity.address.EupMyeonDong;
import java.time.LocalDate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m " +
            "WHERE m.deletedAt is null " +
            "AND m.restaurant.eupMyeonDong = :eupMyeonDong "+
            "ORDER BY m.deadline ASC, m.id DESC")
    Slice<Mission> findFirstPage(EupMyeonDong eupMyeonDong, PageRequest pageRequest);

    @Query("SELECT m FROM Mission m " +
            "WHERE m.deletedAt is null " +
            "AND m.restaurant.eupMyeonDong = :eupMyeonDong "+
            "AND (m.deadline > :deadline OR (m.deadline = :deadline AND m.id < :id)) " +
            "ORDER BY m.deadline ASC, m.id DESC")
    Slice<Mission> findNextPage(EupMyeonDong eupMyeonDong, LocalDate deadline, Long id, PageRequest pageRequest);
}
