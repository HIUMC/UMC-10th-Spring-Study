package com.example.umc10th.domain.region.dto;

import com.example.umc10th.domain.region.enums.TargetRegion;

public class RegionRequestDTO {

    //지역 변경용
    public record UpdateRegionDTO(
            TargetRegion targetRegion
    ) {}
}
