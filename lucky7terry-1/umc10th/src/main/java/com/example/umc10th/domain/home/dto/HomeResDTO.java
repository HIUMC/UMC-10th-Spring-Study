package com.example.umc10th.domain.home.dto;

import java.time.LocalDate;
import java.util.List;

public class HomeResDTO {

    public record HomeResponseDTO(
            Long regionId,
            String regionName,
            List<MissionPreviewDTO> missions
    ) {}

    public record MissionPreviewDTO(
            Long missionId,
            Long storeId,
            String storeName,
            Integer point,
            LocalDate deadline,
            Integer isCompleted
    ) {}
}
