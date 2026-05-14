package com.example.umc10th.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record MyPageDTO(
            String nickname,
            Integer point,
            String email,
            String phoneNumber
    ) {
    }

    @Builder
    public record SignupDTO(
            Long memberId,
            LocalDateTime createdAt
    ) {
    }
}

