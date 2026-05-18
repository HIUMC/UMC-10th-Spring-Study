package com.example.demo.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionPreviewDTO {
        private Long memberMissionId;
        private Long missionId;
        private String storeName;
        private Integer reward;
        private String conditional;
        private String status;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionPreviewListDTO {
        private List<MyMissionPreviewDTO> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AvailableMissionDTO {
        private Long missionId;
        private String storeName;
        private Integer reward;
        private String conditional;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AvailableMissionListDTO {
        private List<AvailableMissionDTO> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    // 가게 내 미션 조회
    @Builder
    public record GetMission(
            Long missionId,
            Integer missionPoint,
            String conditional
    ) {
    }

    // 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {

    }
}