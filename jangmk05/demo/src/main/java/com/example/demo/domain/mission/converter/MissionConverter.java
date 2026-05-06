package com.example.demo.domain.mission.converter;

import com.example.demo.domain.mission.dto.MissionResDTO;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MyMissionPreviewDTO toMyMissionPreviewDTO(MemberMission memberMission) {
        return MissionResDTO.MyMissionPreviewDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .reward(memberMission.getMission().getMissionPoint())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .status(memberMission.getMissionStatus().name())
                .build();
    }

    public static MissionResDTO.MyMissionPreviewListDTO toMyMissionPreviewListDTO(Page<MemberMission> memberMissionPage) {

        List<MissionResDTO.MyMissionPreviewDTO> missionList = memberMissionPage.stream()
                .map(MissionConverter::toMyMissionPreviewDTO)
                .toList();

        return MissionResDTO.MyMissionPreviewListDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }

    public static MissionResDTO.AvailableMissionDTO toAvailableMissionDTO(Mission mission) {
        return MissionResDTO.AvailableMissionDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .reward(mission.getMissionPoint())
                .missionSpec(mission.getMissionSpec())
                .build();
    }

    public static MissionResDTO.AvailableMissionListDTO toAvailableMissionListDTO(Page<Mission> missionPage) {

        List<MissionResDTO.AvailableMissionDTO> missionList = missionPage.stream()
                .map(MissionConverter::toAvailableMissionDTO)
                .toList();

        return MissionResDTO.AvailableMissionListDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
}