package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.region.enums.Address;
import lombok.Builder;

import java.util.List;

public class MissionResponseDTO {

    //미션 목록조회용 - 단건요약
    @Builder
    public record MissionDetailDTO(
            Long missionId,
            String title,
            String description,
            Integer reward,
            String missionCondition,
            String status, // "CHALLENGING", "COMPLETE" 등
            Address address,
            Long regionId,
            String regionName
    ) {}

    //미션 목록 조회용
    @Builder
    public record MissionListDTO(
            List<MissionDetailDTO> missionList
    ) {}
}
