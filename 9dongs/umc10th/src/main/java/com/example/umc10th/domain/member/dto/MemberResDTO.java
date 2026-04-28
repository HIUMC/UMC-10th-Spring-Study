package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record SignUpResultDTO(
            Long memberId,
            String createdAt
    ) {
    }

    @Builder
    public record MyPageDTO(
            String nickname,
            String email,
            String phoneNumber,
            Integer point
    ) {
    }

    @Builder
    public record UpdateRegionResultDTO(
            Long memberId,
            Long regionId,
            String updatedAt
    ) {
    }
}
