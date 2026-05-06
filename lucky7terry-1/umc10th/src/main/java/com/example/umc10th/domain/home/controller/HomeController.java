package com.example.umc10th.domain.home.controller;

import com.example.umc10th.domain.home.dto.HomeReqDTO;
import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.home.service.HomeService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/v1/home")
    public ApiResponse<HomeResDTO.HomeResponseDTO> getHome(
            @ModelAttribute HomeReqDTO.HomeRequest dto
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, homeService.getHome(dto));
    }
}
