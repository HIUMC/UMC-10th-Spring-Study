package com.example.mission4.domain.member.repository;

import com.example.mission4.domain.member.entity.Member;
import com.example.mission4.domain.member.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository  extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String username);

    Optional<Member> findBySocialTypeAndSocialUid(SocialType providerId, String socialUid);
}
