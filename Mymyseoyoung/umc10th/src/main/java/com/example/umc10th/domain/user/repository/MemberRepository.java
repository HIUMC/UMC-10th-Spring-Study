package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
