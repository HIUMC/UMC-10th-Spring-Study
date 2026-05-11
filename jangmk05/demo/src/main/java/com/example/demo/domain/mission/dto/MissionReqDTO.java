package com.example.demo.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

public class MissionReqDTO {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class MissionQueryDTO {
        private String region;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class MissionCompleteDTO {
        @NotNull
        private MissionStatus status;
    }

    public enum MissionStatus {
        ONGOING, COMPLETED
    }

    // 가게 미션 생성
    public record CreateMission(
            @NotNull(message = "마감기한은 필수입니다.")
            LocalDate deadline,
            @NotNull(message = "미션 성공포인트는 필수입니다.")
            Integer missionPoint,
            @NotNull(message = "조건은 빈칸일 수 없습니다")
            String conditional
    ) {
    }

}
