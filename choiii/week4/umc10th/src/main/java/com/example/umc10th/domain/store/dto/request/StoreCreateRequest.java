package com.example.umc10th.domain.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StoreCreateRequest(
        @NotNull Long regionId,
        @NotNull Long foodId,
        @NotBlank String storeName,
        String address,
        Float rating
) {}
