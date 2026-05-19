package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record SignUpRes(
            Long memberId,
            String nickname
    ) {
    }

    @Builder
    public record GetInfo(
            String nickname,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {
    }

    @Builder
    public record UpdateInfo(
            String nickname,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {
    }

    @Builder
    public record GetPoint(
            Integer point
    ) {
    }

    @Builder
    public record UpdatePoint(
            Long memberId,
            Integer point
    ) {
    }
}
