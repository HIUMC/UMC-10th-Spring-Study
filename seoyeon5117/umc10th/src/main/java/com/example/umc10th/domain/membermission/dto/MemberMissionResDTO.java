package com.example.umc10th.domain.membermission.dto;

import com.example.umc10th.domain.membermission.enums.MemberMissionStatus;
import lombok.Builder;

import java.time.LocalDateTime;

public class MemberMissionResDTO {

    @Builder
    public record CreateMemberMission(
            Long memberMissionId,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record GetMemberMission(
            Long memberMissionId,
            Long memberId,
            Long missionId,
            MemberMissionStatus status,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record UpdateMemberMissionStatus(
            Long memberMissionId,
            MemberMissionStatus status,
            LocalDateTime updatedAt
    ) {
    }
}
