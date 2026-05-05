package com.example.week4.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MissionReqDTO {

    // 특정 가게의 미션 조회
    public record StoreMissionListRequest (
            @NotNull(message = "가게 ID는 필수입니다.")
            Long storeId
    ) {
    }

    // 유저 (진행중/완료) 미션 조회
    public record UserMissionListRequest (
            @NotNull(message = "유저 ID는 필수입니다.")
            Long userId,

            @NotBlank(message = "미션 상태는 필수입니다.")
            String missionStatus // 진행 중, 완료
    ) {
    }

    // 미션 도전
    public record ChallengeMissionRequest (
            @NotNull(message = "유저 ID는 필수입니다.")
            Long userId,

            @NotNull(message = "미션 ID는 필수입니다.")
            Long missionId
    ) {
    }
}
