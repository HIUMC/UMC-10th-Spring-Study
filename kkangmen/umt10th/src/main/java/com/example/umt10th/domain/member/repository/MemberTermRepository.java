package com.example.umt10th.domain.member.repository;

import com.example.umt10th.domain.member.entity.mapping.MemberTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberTermRepository extends JpaRepository<MemberTerm, Long> {
}
