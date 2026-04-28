package com.example.umt10th.domain.home.controller;

import com.example.umt10th.domain.home.dto.HomeResDto;
import com.example.umt10th.domain.home.service.HomeService;
import com.example.umt10th.global.apiPayload.ApiResponse;
import com.example.umt10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umt10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/home")
    public ApiResponse<HomeResDto.HomeResponseDto> getHomePage(){

        BaseSuccessCode successCode = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(successCode, homeService.getHomePage());
    }
}
