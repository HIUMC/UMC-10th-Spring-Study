package com.example.umc10th.domain.store.dto;

import lombok.Builder;

public class StoreResDTO {

    // 지역 조회
    @Builder
    public record Region(
        Long id,
        String name
    ) {}
}
