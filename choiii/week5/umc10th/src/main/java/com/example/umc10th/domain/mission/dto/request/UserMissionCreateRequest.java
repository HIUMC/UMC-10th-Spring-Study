package com.example.umc10th.domain.mission.dto.request;

import jakarta.validation.constraints.NotNull;

public record UserMissionCreateRequest(@NotNull Long missionId) {}
