package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.mapping.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
}
