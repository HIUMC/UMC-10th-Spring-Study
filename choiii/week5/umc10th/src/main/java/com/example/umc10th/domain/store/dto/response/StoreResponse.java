package com.example.umc10th.domain.store.dto.response;

import com.example.umc10th.domain.common.enums.RegionName;

public record StoreResponse(
        Long storeId,
        Long regionId,
        RegionName regionName,
        Long foodId,
        String foodName,
        String storeName,
        String address,
        Float rating,
        Integer reviewCount
) {}
