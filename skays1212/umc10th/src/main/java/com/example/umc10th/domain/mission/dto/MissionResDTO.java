package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MissionResDTO {

    /* ───────────── 지역미션 조회 (페이징) ───────────── */
    @Getter
    @Builder
    @AllArgsConstructor
    public static class RegionMissionResDTO {
        private List<MissionItemDTO> missions;
        private int currentPage;
        private int totalPages;
        private long totalElements;
        private boolean isLast;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionItemDTO {
        private Long missionId;
        private String storeName;
        private String region;
        private String missionDescription;
        private Integer rewardPoints;
    }

    /* ───────────── 내 미션 조회 ───────────── */
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionResDTO {
        private List<MyMissionItemDTO> missions;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionItemDTO {
        private Long missionId;
        private String storeName;
        private String missionDescription;
        private Integer rewardPoints;
        private String status; // CHALLENGING, COMPLETED 등
    }

    /* ───────────── 미션 도전 ───────────── */
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionChallengeResDTO {
        private Long memberMissionId;
        private Long missionId;
        private String status;
    }
}
