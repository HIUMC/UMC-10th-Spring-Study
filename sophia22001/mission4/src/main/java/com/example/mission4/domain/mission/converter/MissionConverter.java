package com.example.mission4.domain.mission.converter;

import com.example.mission4.domain.mission.dto.MissionResDTO;
import com.example.mission4.domain.mission.entity.Mission;
import com.example.mission4.domain.mission.entity.mapping.MemberMission;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.GetStoreMission toGetStoreMission(Mission mission) {

        return MissionResDTO.GetStoreMission.builder()
                .missionId(mission.getId())
                .point(mission.getPoint())
                .condition(mission.getCondition())
                .build();
    }

    // 부가적인 pageable 정보를 빼고, 응답 예쁘게 내려주기
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}
