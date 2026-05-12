package com.example.demo.domain.mission.dto;

import com.example.demo.domain.mission.enums.MissionStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

public class MissionRequestDTO {

    @Getter
    public static class HomeMissionQueryRequest {
        private Long regionId;
        private String missionStatus;
        private Integer page = 0;
        private Integer size = 10;
    }

    @Getter
    public static class ChallengeMissionRequest {
        @NotNull(message = "미션 ID는 필수입니다.")
        @Positive(message = "미션 ID는 양수여야 합니다.")
        private Long missionId;
    }

    @Getter
    public static class MemberMissionQueryRequest {
        private MissionStatus status;
        private Integer page = 1;
        private Integer size = 10;
    }

    @Getter
    public static class MemberMissionBodyRequest {
        @NotNull(message = "회원 ID는 필수입니다.")
        @Positive(message = "회원 ID는 양수여야 합니다.")
        private Long memberId;
    }

    @Getter
    public static class CompleteMissionRequest {
        @NotNull(message = "미션 상태는 필수입니다.")
        private MissionStatus status;
    }
}
