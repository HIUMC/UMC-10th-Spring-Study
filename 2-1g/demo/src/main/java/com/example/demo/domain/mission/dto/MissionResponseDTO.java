package com.example.demo.domain.mission.dto;

import com.example.demo.domain.mission.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class MissionResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionPreviewDTO {
        private Long memberMissionId;
        private Long missionId;
        private String storeName;
        private String missionDescription;
        private Long point;
        private LocalDate deadline;
        private MissionStatus status;
    }

    // 미션들 반환
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionListResultDTO {
        private List<MissionPreviewDTO> missionList;
        private Integer page;
        private Integer size;
        private Long totalElements;
        private Integer totalPages;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ChallengeMissionResultDTO {
        private Long memberMissionId;
        private Long missionId;
        private MissionStatus status;
        private LocalDateTime challengedAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CompleteMissionResultDTO {
        private Long memberMissionId;
        private MissionStatus status;
        private LocalDateTime completedAt;
    }
}
