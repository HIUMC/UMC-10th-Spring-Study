package com.example.demo.domain.mission.converter;

import com.example.demo.domain.mission.dto.MissionResponseDTO;
import com.example.demo.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDTO.MissionPreviewDTO toMissionPreviewDTO(MemberMission memberMission) {
        return MissionResponseDTO.MissionPreviewDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .missionDescription(memberMission.getMission().getDescription())
                .point(memberMission.getMission().getPoint())
                .deadline(memberMission.getMission().getDeadline())
                .status(memberMission.getStatus())
                .build();
    }

    public static MissionResponseDTO.MissionListResultDTO toMissionListResultDTO(Page<MemberMission> missionPage) {
        List<MissionResponseDTO.MissionPreviewDTO> previewDTOList = missionPage.stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionListResultDTO.builder()
                .missionList(previewDTOList)
                .page(missionPage.getNumber() + 1)
                .size(missionPage.getSize())
                .totalElements(missionPage.getTotalElements())
                .totalPages(missionPage.getTotalPages())
                .build();
    }
}
