package com.example.umc10th.domain.region.dto.request;

import com.example.umc10th.domain.common.enums.RegionName;
import jakarta.validation.constraints.NotNull;

public record RegionCreateRequest(@NotNull RegionName regionName) {}
