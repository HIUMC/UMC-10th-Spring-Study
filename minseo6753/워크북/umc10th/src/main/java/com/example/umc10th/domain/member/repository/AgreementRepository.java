package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.mapping.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementRepository extends JpaRepository<Agreement, Long>, AgreementRepositoryCustom {
}
