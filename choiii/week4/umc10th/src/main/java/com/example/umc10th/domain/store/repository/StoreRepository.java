package com.example.umc10th.domain.store.repository;

import java.util.List;

import com.example.umc10th.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
