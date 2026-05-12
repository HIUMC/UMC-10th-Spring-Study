package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    // 홈화면 상단 - 내 포인트, 수행미션수 조회
    @Builder
    public record GetInfo(
            Integer point,
            Integer currentMissionCount
    ) {}

    // 마이페이지 조회
    @Builder
    public record GetMyPage(
            String nickname,
            String email,
            String phoneNumber
    ) {}
}
