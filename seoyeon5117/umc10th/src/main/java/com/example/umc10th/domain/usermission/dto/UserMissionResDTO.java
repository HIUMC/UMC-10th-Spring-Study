package com.example.umc10th.domain.usermission.dto;

import com.example.umc10th.domain.usermission.enums.UserMissionStatus;
import lombok.Builder;

import java.time.LocalDateTime;

public class UserMissionResDTO {

    @Builder
    public record CreateUserMission(
            Long userMissionId,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record GetUserMission(
            Long userMissionId,
            Long memberId,
            Long missionId,
            UserMissionStatus status,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record UpdateUserMissionStatus(
            Long userMissionId,
            UserMissionStatus status,
            LocalDateTime updatedAt
    ) {
    }
}
