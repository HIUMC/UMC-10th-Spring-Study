package com.example.demo.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
