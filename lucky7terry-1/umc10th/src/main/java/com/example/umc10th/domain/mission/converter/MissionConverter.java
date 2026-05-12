package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionListDTO toMissionListDTO(
            List<MemberMission> memberMissions,
            Boolean hasNext,
            Long nextCursor
    ) {
        List<MissionResDTO.MissionPreviewDTO> missionList = memberMissions.stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .toList();

        return MissionResDTO.MissionListDTO.builder()
                .missionList(missionList)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();
    }

    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(MemberMission memberMission) {
        boolean completed = Boolean.TRUE.equals(memberMission.getIsCompleted());

        return MissionResDTO.MissionPreviewDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getStoreName())
                .point(memberMission.getMission().getPoint())
                .status(completed ? "진행 완료" : "진행 중")
                .content(memberMission.getContent())
                .deadline(memberMission.getMission().getDeadline())
                .build();
    }

    public static MissionResDTO.MissionCompleteDTO toMissionCompleteDTO(MemberMission memberMission) {
        return MissionResDTO.MissionCompleteDTO.builder()
                .missionId(memberMission.getMission().getId())
                .isCompleted(memberMission.getIsCompleted())
                .build();
    }
}

