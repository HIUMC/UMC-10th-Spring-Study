package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MemberMissionStatus;

import jakarta.validation.constraints.NotNull;

public class MissionReqDTO {

    public record MyMissionReq(
            @NotNull(message = "memberId는 필수입니다.")
            Long memberId
    ) {
    }

    public record StartMissionDTO(
            Long missionId
    ) {
    }

    public record UpdateStatusDTO(
            MemberMissionStatus status
    ) {
    }
}
