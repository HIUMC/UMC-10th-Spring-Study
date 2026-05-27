package com.example.umt10th.domain.member.repository;

import com.example.umt10th.domain.member.entity.Member;
import com.example.umt10th.domain.member.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long memberId);

    Optional<Member> findByEmail(String username);

    Optional<Member> findByName(String username);

    Optional<Member> findBySocialTypeAndSocialUid(SocialType socialType, String socialUid);
}
