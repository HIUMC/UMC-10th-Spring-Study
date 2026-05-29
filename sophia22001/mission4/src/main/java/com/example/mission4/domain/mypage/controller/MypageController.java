package com.example.mission4.domain.mypage.controller;

import com.example.mission4.domain.mypage.dto.MypageResDTO;
import com.example.mission4.domain.mypage.exception.MypageException;
import com.example.mission4.domain.mypage.exception.code.MypageSuccessCode;
import com.example.mission4.domain.mypage.service.MypageService;
import com.example.mission4.global.apiPayload.ApiResponse;
import com.example.mission4.global.apiPayload.code.BaseSuccessCode;
import com.example.mission4.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MypageController {

    public final MypageService mypageService;

    // 마이페이지 정보 조회
    // 헤더의 토큰으로 사용자 정보 조회한다.
    @GetMapping
    public ApiResponse<MypageResDTO.GetMypage> getMypage(
            // @AuthenticationPrincipal: SecurityContextHolder에서 인증 객체를 뽑아올 수 있다.
            @AuthenticationPrincipal AuthMember member
    ) {
        BaseSuccessCode code = MypageSuccessCode.HOME_FOUND;
        return ApiResponse.onSuccess(code, mypageService.getMypage(member));
    }

}
