package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionDTO toMission(Mission mission) {
        return MissionResDTO.MissionDTO.builder()
                .missionId(mission.getId())
                .content(mission.getContent())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .build();
    }


    public static MissionResDTO.MissionListDTO toMissionList(List<Mission> missionList) {

        List<MissionResDTO.MissionDTO> missionDTOList = missionList.stream()
                .map(MissionConverter::toMission)
                .toList();


        return MissionResDTO.MissionListDTO.builder()
                .missionList(missionDTOList)
                .lastMissionId(missionDTOList.isEmpty() ? null : missionDTOList.get(missionDTOList.size() - 1).missionId())
                .hasNext(false)
                .build();
    }


}
