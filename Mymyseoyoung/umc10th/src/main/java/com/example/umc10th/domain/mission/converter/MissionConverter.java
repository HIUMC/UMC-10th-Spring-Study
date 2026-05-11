package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.store.entity.Store;

import java.util.List;


public class MissionConverter {

    public static Mission toMission(Store store, MissionRequestDTO.CreateMission request)
    {
        return Mission.builder()
                .store(store)
                .conditional(request.conditional())
                .rewardPoints(request.point())
                .deadLine(request.deadLine())
                .build();
    }

    // 가게 내 미션 조회 관련
    public static MissionResponseDTO.GetMission toGetMission(Mission mission)
    {
        return MissionResponseDTO.GetMission.builder()
                .conditional(mission.getConditional())
                .point(mission.getRewardPoints())
                .missionId(mission.getId())
                .build();
    }

    //페이지네이션 틀 생성

    public static <T> MissionResponseDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    )
    {
        return MissionResponseDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize).build();

    }
}
