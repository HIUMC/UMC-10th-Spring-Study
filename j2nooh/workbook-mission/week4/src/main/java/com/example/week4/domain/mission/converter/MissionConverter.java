package com.example.week4.domain.mission.converter;

import com.example.week4.domain.mission.dto.MissionResDTO;
import com.example.week4.domain.mission.entity.Mission;
import com.example.week4.domain.mission.entity.mapping.UserMission;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionResponse toMissionResponse(Mission mission) {
        return MissionResDTO.MissionResponse.builder()
                .missionId(mission.getId())
                .missionName(mission.getMissionName())
                .missionCondition(mission.getMissionCondition())
                .rewardPoint(mission.getRewardPoint())
                .endDate(mission.getEndDate())
                .storeId(mission.getStore().getId())
                .build();
    }

    public static MissionResDTO.MissionListResponse toMissionListResponse(List<Mission> missions) {
        List<MissionResDTO.MissionResponse> missionResponses = missions.stream()
                .map(MissionConverter::toMissionResponse)
                .toList();
        return MissionResDTO.MissionListResponse.builder()
                .missions(missionResponses)
                .build();
    }

    public static MissionResDTO.UserMissionResponse toUserMissionResponse(UserMission userMission) {
        return MissionResDTO.UserMissionResponse.builder()
                .userMissionId(userMission.getId())
                .missionStatus(userMission.getMissionStatus().name())
                .assignedAt(userMission.getAssignedAt())
                .completedAt(userMission.getCompletedAt())
                .userId(userMission.getUser().getId())
                .missionId(userMission.getMission().getId())
                .build();
    }

    public static MissionResDTO.UserMissionListResponse toUserMissionListResponse(List<UserMission> userMissions) {
        List<MissionResDTO.UserMissionResponse> userMissionResponses = userMissions.stream()
                .map(MissionConverter::toUserMissionResponse)
                .toList();

        return MissionResDTO.UserMissionListResponse.builder()
                .userMissions(userMissionResponses)
                .build();
    }
}
