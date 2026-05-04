package com.example.umc10th.domain.point.repository;

import com.example.umc10th.domain.point.entity.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<PointHistory, Long> {
}
