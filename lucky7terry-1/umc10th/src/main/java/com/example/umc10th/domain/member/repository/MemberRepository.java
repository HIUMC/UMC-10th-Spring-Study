package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByIdAndDeletedAtIsNull(Long id);

    @Query("SELECT m FROM Member m WHERE m.name = :nmae AND m.deletedAt is null")
    Optional<Member> findActiveMember(String name);
}
