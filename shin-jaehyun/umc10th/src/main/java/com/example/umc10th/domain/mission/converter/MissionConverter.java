package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.getMissions toMission(Mission mission) {
        return MissionResDTO.getMissions.builder()
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

    public static <T> MissionResDTO.Pagination<T> toPagination(List<T> data, Integer pageNumber, Integer pageSize) {
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}
