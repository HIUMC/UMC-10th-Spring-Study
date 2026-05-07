package com.example.week4.domain.store.controller;

import com.example.week4.domain.store.dto.StoreReqDTO;
import com.example.week4.domain.store.dto.StoreResDTO;
import com.example.week4.domain.store.exception.code.StoreSuccessCode;
import com.example.week4.domain.store.service.StoreService;
import com.example.week4.global.apiPayload.ApiResponse;
import com.example.week4.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StoreController {

    private final StoreService storeService;

    // 가게 단건 조회
    @GetMapping("/v1/stores/info")
    public ApiResponse<StoreResDTO.StoreInfoResponse> getStoreInfo(
            @RequestParam Long storeId
    ) {
        BaseSuccessCode code = StoreSuccessCode.GET_STORE;
        StoreReqDTO.StoreInfoRequest dto = new StoreReqDTO.StoreInfoRequest(storeId);
        StoreResDTO.StoreInfoResponse response = storeService.getStoreInfo(dto);

        return ApiResponse.onSuccess(code, response);
    }

    // 지역별 가게 조회
    @GetMapping("/v1/stores/region")
    public ApiResponse<StoreResDTO.StoreListResponse> getStoresByRegion(
            @RequestParam String storeRegion
    ) {
        BaseSuccessCode code = StoreSuccessCode.GET_STORE_LIST;
        StoreReqDTO.StoreRegionRequest dto = new StoreReqDTO.StoreRegionRequest(storeRegion);
        StoreResDTO.StoreListResponse response = storeService.getStoresByRegion(dto);

        return ApiResponse.onSuccess(code, response);
    }
}
