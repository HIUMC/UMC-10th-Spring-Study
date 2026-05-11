package com.example.demo.domain.mission.dto;

import com.example.demo.domain.mission.enums.MissionStatus;
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
        private Long memberId;
    }

    @Getter
    public static class CompleteMissionRequest {
        private MissionStatus status;
    }
}
