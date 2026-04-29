package com.example.umc10th.domain.store.converter;

import com.example.umc10th.domain.food.entity.Food;
import com.example.umc10th.domain.region.entity.Region;
import com.example.umc10th.domain.store.dto.request.StoreCreateRequest;
import com.example.umc10th.domain.store.dto.response.StoreResponse;
import com.example.umc10th.domain.store.entity.Store;

public class StoreConverter {

    private StoreConverter() {}

    public static Store toEntity(StoreCreateRequest request, Region region, Food food) {
        return Store.builder()
                .region(region)
                .food(food)
                .storeName(request.storeName())
                .address(request.address())
                .rating(request.rating())
                .reviewCount(0)
                .build();
    }

    public static StoreResponse toResponse(Store store) {
        return new StoreResponse(
                store.getId(),
                store.getRegion().getId(),
                store.getRegion().getRegionName(),
                store.getFood().getId(),
                store.getFood().getFoodName(),
                store.getStoreName(),
                store.getAddress(),
                store.getRating(),
                store.getReviewCount()
        );
    }
}
