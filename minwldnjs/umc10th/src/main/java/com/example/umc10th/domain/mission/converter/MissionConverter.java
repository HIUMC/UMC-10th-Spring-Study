package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MissionDetailDTO toMissionDetailDTO(MemberMission memberMission) {
        return MissionResDTO.MissionDetailDTO.builder()
                .missionId(memberMission.getMission().getId())
                .title(memberMission.getMission().getMissionContent())
                .reward(memberMission.getMission().getPoint())
                .status(memberMission.getComplete() ? "완료" : "진행중")
                .build();
    }

    public static MissionResDTO.MissionPageDTO toMissionPageDTO(Page<MemberMission> page) {
        List<MissionResDTO.MissionDetailDTO> missionList = page.stream()
                .map(MissionConverter::toMissionDetailDTO)
                .collect(Collectors.toList());

        return MissionResDTO.MissionPageDTO.builder()
                .missionList(missionList)
                .currentPage(page.getNumber() + 1)
                .totalPage(page.getTotalPages())
                .totalCount(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}