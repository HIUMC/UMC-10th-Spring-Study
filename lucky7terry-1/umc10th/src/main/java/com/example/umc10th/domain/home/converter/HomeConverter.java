package com.example.umc10th.domain.home.converter;

import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Region;

import java.util.List;

public class HomeConverter {

    public static HomeResDTO.HomeResponseDTO toHomeResponseDTO(
            Region region,
            Long clearedMissionCount,
            List<Mission> missions,
            Boolean hasNext,
            Long nextCursor
    ) {
        return HomeResDTO.HomeResponseDTO.builder()
                .currentRegion(toRegionDTO(region))
                .clearedMissionCount(clearedMissionCount)
                .missionList(missions.stream().map(HomeConverter::toMissionPreviewDTO).toList())
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();
    }

    private static HomeResDTO.RegionDTO toRegionDTO(Region region) {
        return HomeResDTO.RegionDTO.builder()
                .regionId(region.getId())
                .locateName(region.getRegion())
                .build();
    }

    private static HomeResDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return HomeResDTO.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getStoreName())
                .category(mission.getStore().getCategory().getCategoryName())
                .deadline(mission.getDeadline())
                .point(mission.getPoint())
                .build();
    }
}
