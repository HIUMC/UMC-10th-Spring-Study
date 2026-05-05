package com.example.umc10th.domain.region.controller;


import com.example.umc10th.domain.region.dto.RegionRequestDTO;
import com.example.umc10th.domain.region.dto.RegionResponseDTO;
import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/regions")
@RequiredArgsConstructor
public class RegionController {

    //미션 지역 변경
    @PatchMapping
    public ApiResponse<RegionResponseDTO.UpdateRegionResultDTO> updateRegion(
            @RequestBody RegionRequestDTO.UpdateRegionDTO request) {

        // TODO: RegionSuccessCode.REGION_UPDATED 추가 후 사용
        return ApiResponse.onSuccess(null, null);
    }

    //진행률 표시
    @GetMapping("/progress")
    public ApiResponse<RegionResponseDTO.GetProgressDTO> getRegionProgress(
            @RequestBody MemberRequestDTO.GetInfo userDto) {

        // TODO: RegionSuccessCode.PROGRESS_FOUND 추가 후 사용
        return ApiResponse.onSuccess(null, null);
    }

}
