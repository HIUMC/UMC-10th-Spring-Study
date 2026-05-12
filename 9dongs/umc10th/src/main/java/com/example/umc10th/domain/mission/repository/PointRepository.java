package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long> {
    // 유저의 총 포인트 합산
    @Query("SELECT SUM(p.pointChange) FROM Point p WHERE p.member.id = :memberId")
    Optional<Integer> sumPointChangeByMemberId(@Param("memberId") Long memberId);
}