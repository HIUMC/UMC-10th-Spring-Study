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
        private String conditional;
        private int point;
    }

    /* ───────────── 내 미션 조회 ───────────── */
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionResDTO {
        private List<MyMissionItemDTO> missions;
        private int currentPage;
        private int totalPages;
        private long totalElements;
        private boolean isLast;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionItemDTO {
        private Long missionId;
        private String storeName;
        private String conditional;
        private int point;
        private Boolean isComplete;
    }

    /* ───────────── 미션 도전 ───────────── */
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionChallengeResDTO {
        private Long memberMissionId;
        private Long missionId;
        private Boolean isComplete;
    }
}
