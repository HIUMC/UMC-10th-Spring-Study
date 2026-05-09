package com.example.mission4.domain.home.controller;

import com.example.mission4.domain.home.dto.HomeResDTO;
import com.example.mission4.domain.home.exception.code.HomeSuccessCode;
import com.example.mission4.domain.home.service.HomeService;
import com.example.mission4.global.apiPayload.ApiResponse;
import com.example.mission4.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class HomeController {

    private final HomeService homeService;

    // 홈화면 정보 조회
    @GetMapping
    public ApiResponse<HomeResDTO.GetHome> getHome(@PathVariable Long memberId) {
        BaseSuccessCode code = HomeSuccessCode.HOME_FOUND;
        return ApiResponse.onSuccess(code, homeService.getHome(memberId));
    }
}
