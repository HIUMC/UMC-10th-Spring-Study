package com.example.umc10th.domain.userterm.controller;

import com.example.umc10th.domain.userterm.dto.UserTermReqDTO;
import com.example.umc10th.domain.userterm.dto.UserTermResDTO;
import com.example.umc10th.domain.userterm.enums.UserTermSuccessCode;
import com.example.umc10th.domain.userterm.service.UserTermService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-terms")
public class UserTermController {

    private final UserTermService userTermService;

    // 첫 약관 동의
    @PostMapping
    public ApiResponse<UserTermResDTO.AgreeTerm> agreeTerm(
            @RequestBody UserTermReqDTO.AgreeTerm dto
    ) {
        BaseSuccessCode code = UserTermSuccessCode.USER_TERM_AGREE;
        return ApiResponse.onSuccess(code, userTermService.agreeTerm());
    }

    // 유저 약관 목록 조회
    @GetMapping
    public ApiResponse<List<UserTermResDTO.GetUserTerm>> getUserTerms(
            @RequestParam Long memberId
    ) {
        BaseSuccessCode code = UserTermSuccessCode.USER_TERM_GET;
        return ApiResponse.onSuccess(code, userTermService.getUserTerms());
    }
}
