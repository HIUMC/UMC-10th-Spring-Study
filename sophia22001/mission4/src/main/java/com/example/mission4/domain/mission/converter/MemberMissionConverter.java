package com.example.mission4.domain.mission.converter;

import com.example.mission4.domain.mission.dto.MissionResDTO;
import com.example.mission4.domain.mission.entity.mapping.MemberMission;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {

    // 단건 변환 (엔티티 -> DTO)
    public static MissionResDTO.GetMissions toGetMission(MemberMission memberMission) {
        return MissionResDTO.GetMissions.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .point(memberMission.getMission().getPoint())
                .condition(memberMission.getMission().getCondition())
                .build();
    }

    // 리스트 변환 (엔티티 리스트 -> DTO 리스트)
    public static List<MissionResDTO.GetMissions> toGetMissionsList(List<MemberMission> memberMissions) {
        return memberMissions.stream()
                .map(MemberMissionConverter::toGetMission)
                .collect(Collectors.toList());
    }
}
