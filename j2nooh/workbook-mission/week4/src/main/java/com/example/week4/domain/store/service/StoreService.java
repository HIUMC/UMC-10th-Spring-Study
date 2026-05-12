package com.example.week4.domain.store.service;

import com.example.week4.domain.store.converter.StoreConverter;
import com.example.week4.domain.store.dto.StoreReqDTO;
import com.example.week4.domain.store.dto.StoreResDTO;
import com.example.week4.domain.store.entity.Store;
import com.example.week4.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreResDTO.StoreInfoResponse getStoreInfo(StoreReqDTO.StoreInfoRequest dto) {
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        return StoreConverter.toStoreInfoResponse(store);
    }

    public StoreResDTO.StoreListResponse getStoresByRegion(StoreReqDTO.StoreRegionRequest dto) {
        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<Store> storePage = storeRepository.findStoresByRegion(
                dto.storeRegion(),
                pageRequest
        );

        return StoreConverter.toStoreListResponse(storePage.getContent());
    }
}