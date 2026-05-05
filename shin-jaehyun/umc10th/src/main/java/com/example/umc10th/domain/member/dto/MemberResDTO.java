package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    // 회원가입
    @Builder
    public record SignupResult(
        Long id
    ) {}

    // 마이페이지
    @Builder
    public record GetInfo(
        String name,
        String email,
        String phoneNumber,
        Long point
    ) {}
}
