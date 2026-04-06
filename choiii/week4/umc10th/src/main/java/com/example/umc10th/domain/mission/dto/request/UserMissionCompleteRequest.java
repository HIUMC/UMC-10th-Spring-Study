package com.example.umc10th.domain.mission.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserMissionCompleteRequest(@NotBlank String verificationCode) {}
