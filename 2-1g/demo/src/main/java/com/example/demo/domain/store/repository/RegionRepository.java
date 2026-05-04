package com.example.demo.domain.store.repository;

import com.example.demo.domain.store.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
