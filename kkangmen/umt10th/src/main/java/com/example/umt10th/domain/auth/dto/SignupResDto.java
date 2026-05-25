package com.example.umt10th.domain.auth.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class SignupResDto {

    @Builder
    public record SignupComp(
            LocalDateTime createdAt
    ){}
}
