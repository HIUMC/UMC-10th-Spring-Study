package com.example.mission4.domain.mypage.controller;

import com.example.mission4.domain.mypage.dto.MypageResDTO;
import com.example.mission4.domain.mypage.exception.MypageException;
import com.example.mission4.domain.mypage.exception.code.MypageSuccessCode;
import com.example.mission4.domain.mypage.service.MypageService;
import com.example.mission4.global.apiPayload.ApiResponse;
import com.example.mission4.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MypageController {

    public final MypageService mypageService;

    // 마이페이지 정보 조회
    @GetMapping
    public ApiResponse< MypageResDTO.GetMypage > getMypage(@RequestParam Long memberId) {
        BaseSuccessCode code = MypageSuccessCode.HOME_FOUND;
        return ApiResponse.onSuccess(code, mypageService.getMypage(memberId));
    }

}
