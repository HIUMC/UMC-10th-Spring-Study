package com.example.week4.domain.store.service;

import com.example.week4.domain.store.dto.StoreReqDTO;
import com.example.week4.domain.store.dto.StoreResDTO;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
    public StoreResDTO.StoreInfoResponse getStoreInfo(StoreReqDTO.StoreInfoRequest dto) {
        return null;
    }

    public StoreResDTO.StoreListResponse getStoresByRegion(StoreReqDTO.StoreRegionRequest dto) {
        return null;
    }
}
