package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.MemberAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberAddressRepository extends JpaRepository<MemberAddress, Long> {

    // 본인 주소 확인
    Optional<MemberAddress> findByIdAndMemberId(Long id, Long memberId);
}