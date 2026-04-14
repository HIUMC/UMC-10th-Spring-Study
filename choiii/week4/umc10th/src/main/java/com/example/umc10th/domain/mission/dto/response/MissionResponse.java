package com.example.umc10th.domain.mission.dto.response;

import com.example.umc10th.domain.common.enums.MissionType;
import java.time.LocalDate;

public record MissionResponse(
        Long missionId,
        String missionName,
        Integer rewardPoint,
        LocalDate dueDays,
        MissionType missionType,
        Long storeId,
        String storeName
) {}
