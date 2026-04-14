package com.example.umc10th.domain.store.service;

import com.example.umc10th.domain.food.entity.Food;
import com.example.umc10th.domain.food.service.FoodService;
import com.example.umc10th.domain.region.entity.Region;
import com.example.umc10th.domain.region.service.RegionService;
import com.example.umc10th.domain.store.converter.StoreConverter;
import com.example.umc10th.domain.store.dto.request.StoreCreateRequest;
import com.example.umc10th.domain.store.dto.response.StoreResponse;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.global.exception.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final RegionService regionService;
    private final FoodService foodService;

    @Transactional
    public StoreResponse createStore(StoreCreateRequest request) {
        Region region = regionService.findRegion(request.regionId());
        Food food = foodService.findFood(request.foodId());
        Store store = storeRepository.save(StoreConverter.toEntity(request, region, food));
        return StoreConverter.toResponse(store);
    }

    public StoreResponse getStore(Long storeId) {
        return StoreConverter.toResponse(findStore(storeId));
    }

    public List<StoreResponse> getStores() {
        return storeRepository.findAll().stream().map(StoreConverter::toResponse).toList();
    }

    public Store findStore(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 가게입니다. storeId=" + storeId));
    }
}
