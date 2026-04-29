package com.example.umc10th.domain.mission.dto.request;

import com.example.umc10th.domain.common.enums.MissionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record MissionCreateRequest(
        @NotBlank String missionName,
        Integer rewardPoint,
        @NotNull LocalDate dueDays,
        @NotNull MissionType missionType,
        @NotNull Long storeId
) {}
