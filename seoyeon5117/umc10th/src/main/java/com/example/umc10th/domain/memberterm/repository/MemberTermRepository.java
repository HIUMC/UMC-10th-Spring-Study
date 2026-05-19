package com.example.umc10th.domain.memberterm.repository;

import com.example.umc10th.domain.memberterm.entity.MemberTerm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTermRepository extends JpaRepository<MemberTerm, Long> {
}