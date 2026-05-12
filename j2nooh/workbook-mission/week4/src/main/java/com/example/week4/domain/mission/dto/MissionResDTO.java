package com.example.week4.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    public static class MissionResponse {
        private Long missionId;
        private String missionName;
        private String missionCondition;
        private Integer rewardPoint;
        private LocalDate endDate;
        private Long storeId;
    }

    @Getter
    @Builder
    public static class MissionListResponse {
        private List<MissionResponse> missions;
    }

    @Getter
    @Builder
    public static class UserMissionResponse {
        private Long userMissionId;
        private String missionStatus;
        private LocalDateTime assignedAt;
        private LocalDateTime completedAt;
        private Long userId;
        private Long missionId;
    }

    @Getter
    @Builder
    public static class UserMissionListResponse {
        private List<UserMissionResponse> userMissions;

        private Integer pageNumber;
        private Integer pageSize;
        private Long totalElements;
        private Integer totalPages;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
