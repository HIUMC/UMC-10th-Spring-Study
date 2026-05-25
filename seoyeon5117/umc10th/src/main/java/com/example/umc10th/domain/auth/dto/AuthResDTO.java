package com.example.umc10th.domain.auth.dto;

import lombok.Builder;

public class AuthResDTO {

    @Builder
    public record SignUp(
            Long memberId,
            String nickname
    ) {
    }

    @Builder
    public record Login(
            String accessToken
    ) {
    }
}
