package com.example.week4.domain.store.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class StoreResDTO {

    @Getter
    @Builder
    public static class StoreInfoResponse {
        private Long storeId;
        private String storeName;
        private String storeRegion;
        private String ownerCode;
        private String storeCategory;
    }

    @Getter
    @Builder
    public static class StoreListResponse {
        private List<StoreInfoResponse> stores;
    }
}
