package com.example.week4.domain.store.converter;

import com.example.week4.domain.store.dto.StoreResDTO;
import com.example.week4.domain.store.entity.Store;

import java.util.List;

public class StoreConverter {

    public static StoreResDTO.StoreInfoResponse toStoreInfoResponse(Store store) {
        return StoreResDTO.StoreInfoResponse.builder()
                .storeId(store.getId())
                .storeName(store.getStoreName())
                .storeRegion(store.getStoreRegion())
                .ownerCode(store.getOwnerCode())
                .storeCategory(store.getStoreCategory().name())
                .build();
    }

    public static StoreResDTO.StoreListResponse toStoreListResponse(List<Store> stores) {
        List<StoreResDTO.StoreInfoResponse> storeResponses = stores.stream()
                .map(StoreConverter::toStoreInfoResponse)
                .toList();

        return StoreResDTO.StoreListResponse.builder()
                .stores(storeResponses)
                .build();
    }
}