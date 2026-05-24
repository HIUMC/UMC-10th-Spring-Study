package com.example.demo.domain.member.repository;

import com.example.demo.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 로그인할 때 email을 이용해 멤버를 찾기 위함

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
}
