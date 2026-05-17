package com.example.umc10th.domain.auth.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class AuthResDTO {

    @Builder
    public record SignupDTO(
            Long memberId,
            LocalDateTime createdAt
    ) {}

}
