package com.example.umt10th.domain.member.repository;

import com.example.umt10th.domain.member.entity.Term;
import com.example.umt10th.domain.member.enums.TermName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {

    Optional<Term> findByTermName(TermName termName);
}
