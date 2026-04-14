package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.store.dto.request.StoreCreateRequest;
import com.example.umc10th.domain.store.dto.response.StoreResponse;
import com.example.umc10th.domain.store.service.StoreService;
import com.example.umc10th.global.api.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ApiResponse<StoreResponse> createStore(@RequestBody @Valid StoreCreateRequest request) {
        return ApiResponse.created(storeService.createStore(request));
    }

    @GetMapping
    public ApiResponse<List<StoreResponse>> getStores() {
        return ApiResponse.ok(storeService.getStores());
    }

    @GetMapping("/{storeId}")
    public ApiResponse<StoreResponse> getStore(@PathVariable Long storeId) {
        return ApiResponse.ok(storeService.getStore(storeId));
    }
}
