package com.example.demo.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionPreviewDTO {
        private Long id;
        private String title;
        private String distance;
        private Integer point;
        private String category;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionListDTO {
        private List<MissionPreviewDTO> missions;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionCompleteResultDTO {
        private Long missionId;
        private String status;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class HomeMissionDTO {
        private Long id;
        private String title;
        private String content;
        private Integer point;
        private String category;
        private LocalDate receivedAt;
        private String status;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionProgressDTO {
        private Integer completedCount;
        private Integer totalCount;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MainRewardDTO {
        private Integer point;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class HomeDTO {
        private String region;
        private Integer myPoint;
        private MissionProgressDTO missionProgress;
        private MainRewardDTO mainReward;
        private List<HomeMissionDTO> missions;
    }
}
