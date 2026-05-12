package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    // Mission -> MissionResDTO.MissionInfo
    public static MissionResDTO.MissionInfo toMissionInfo(Mission mission, LocalDate today) {
        return MissionResDTO.MissionInfo.builder()
                .missionId(mission.getId())
                .missionDetail(mission.getMissionDetail())
                .deadline(mission.getDeadline())
                .successPoint(mission.getSuccessPoint())
                .dDay(ChronoUnit.DAYS.between(today, mission.getDeadline()))
                .storeName(mission.getStore().getName())
                .storeCategory(mission.getStore().getStoreCategory())
                .build();
    }

    // MemberMission -> MissionResDTO.MyMissionInfo
    public static MissionResDTO.MyMissionInfo toMyMissionInfo(MemberMission memberMission) {
        return MissionResDTO.MyMissionInfo.builder()
                .memberMissionId(memberMission.getId())
                .missionDetail(memberMission.getMission().getMissionDetail())
                .deadline(memberMission.getMission().getDeadline())
                .successPoint(memberMission.getMission().getSuccessPoint())
                .missionComplete(memberMission.getMissionComplete())
                .build();
    }

    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data, Integer pageSize, Integer pageNumber
    ) {
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}
