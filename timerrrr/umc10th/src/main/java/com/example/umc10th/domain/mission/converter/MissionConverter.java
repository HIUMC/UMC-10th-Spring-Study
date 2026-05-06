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

    // List<Mission> -> List<MissionResDTO.MissionInfo>
    public static List<MissionResDTO.MissionInfo> toMissionInfoList(List<Mission> missions, LocalDate today) {
        return missions.stream()
                .map(mission -> toMissionInfo(mission, today))
                .collect(Collectors.toList());
    }

    // MemberMission -> MissionResDTO.MyMissionInfo
    public static MissionResDTO.MyMissionInfo toMyMissionInfo(MemberMission memberMission) {
        return MissionResDTO.MyMissionInfo.builder()
                .memberMissionId(memberMission.getId())
                .missionDetail(memberMission.getMission().getMissionDetail())
                .deadline(memberMission.getMission().getDeadline())
                .successPoint(memberMission.getMission().getSuccessPoint())
                .missionComplete(memberMission.getIsMissionComplete())
                .build();
    }

    // List<MemberMission> -> List<MissionResDTO.MyMissionInfo>
    public static List<MissionResDTO.MyMissionInfo> toMyMissionInfoList(List<MemberMission> memberMissions) {
        return memberMissions.stream()
                .map(MissionConverter::toMyMissionInfo)
                .collect(Collectors.toList());
    }
}
