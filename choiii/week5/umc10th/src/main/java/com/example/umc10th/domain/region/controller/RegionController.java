package com.example.umc10th.domain.region.controller;

import com.example.umc10th.domain.region.dto.request.RegionCreateRequest;
import com.example.umc10th.domain.region.dto.response.RegionResponse;
import com.example.umc10th.domain.region.service.RegionService;
import com.example.umc10th.global.api.ApiResponse;
import com.example.umc10th.global.api.code.CommonSuccessCode;
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
        return ApiResponse.onSuccess(
                CommonSuccessCode.CREATED,
                regionService.createRegion(request)
        );    }
}
