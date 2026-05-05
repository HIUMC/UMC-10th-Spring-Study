package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.common.enums.MissionStatus;
import com.example.umc10th.domain.mission.dto.request.MissionCreateRequest;
import com.example.umc10th.domain.mission.dto.response.MissionResponse;
import com.example.umc10th.domain.mission.dto.response.UserMissionResponse;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.UserMission;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.user.entity.User;

public class MissionConverter {

    private MissionConverter() {}

    public static Mission toEntity(MissionCreateRequest request, Store store) {
        return Mission.builder()
                .missionName(request.missionName())
                .rewardPoint(request.rewardPoint())
                .dueDays(request.dueDays())
                .missionType(request.missionType())
                .store(store)
                .build();
    }

    public static UserMission toUserMissionEntity(User user, Mission mission) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();
    }

    public static MissionResponse toMissionResponse(Mission mission) {
        return new MissionResponse(
                mission.getId(), mission.getMissionName(), mission.getRewardPoint(),
                mission.getDueDays(), mission.getMissionType(),
                mission.getStore().getId(), mission.getStore().getStoreName()
        );
    }

    public static UserMissionResponse toUserMissionResponse(UserMission userMission) {
        return new UserMissionResponse(
                userMission.getId(), userMission.getUser().getId(), userMission.getMission().getId(),
                userMission.getMission().getMissionName(), userMission.getStatus(),
                userMission.getVerificationCode(), userMission.getCompletedAt()
        );
    }
}
