package com.example.umc10th.domain.mission.dto.response;

import com.example.umc10th.domain.common.enums.MissionStatus;
import java.time.LocalDateTime;

public record UserMissionResponse(
        Long userMissionId,
        Long userId,
        Long missionId,
        String missionName,
        MissionStatus status,
        String verificationCode,
        LocalDateTime completedAt
) {}
