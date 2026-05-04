package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;

public class MissionConverter {

    public static MissionResDTO.Missions toMission(Mission mission) {
        return MissionResDTO.Missions.builder()
                .storeName(mission.getStore().getName())
                .foodCategory(mission.getStore().getFoodCategory().getName())
                .content(mission.getContent())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResDTO.MissionsCount toMissionCount(Long count) {
        return MissionResDTO.MissionsCount.builder()
                .count(count)
                .build();
    }
}
