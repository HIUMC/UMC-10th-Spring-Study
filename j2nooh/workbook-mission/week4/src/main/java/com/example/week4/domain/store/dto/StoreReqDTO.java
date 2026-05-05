package com.example.week4.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StoreReqDTO {

    // 가게 단건 조회
    public record StoreInfoRequest(
            @NotNull(message = "가게 ID는 필수입니다.")
            Long storeId
    ) {
    }

    // 지역별 가게 조회
    public record StoreRegionRequest(
            @NotBlank(message = "가게 지역은 필수입니다.")
            String storeRegion
    ) {
    }
}
