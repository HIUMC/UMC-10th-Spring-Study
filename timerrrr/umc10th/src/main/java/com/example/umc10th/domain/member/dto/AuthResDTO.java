package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class AuthResDTO {

    // 로그인 응답
    @Builder
    public record Login(
            String accessToken
    ) {}
}
