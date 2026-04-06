package com.example.umc10th.domain.region.controller;

import com.example.umc10th.domain.region.dto.request.RegionCreateRequest;
import com.example.umc10th.domain.region.dto.response.RegionResponse;
import com.example.umc10th.domain.region.service.RegionService;
import com.example.umc10th.global.api.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/regions")
public class RegionController {

    private final RegionService regionService;

    @PostMapping
    public ApiResponse<RegionResponse> createRegion(@RequestBody @Valid RegionCreateRequest request) {
        return ApiResponse.created(regionService.createRegion(request));
    }
}
