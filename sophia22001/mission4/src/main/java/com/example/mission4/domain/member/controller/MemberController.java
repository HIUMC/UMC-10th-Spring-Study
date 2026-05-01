package com.example.mission4.domain.member.controller;

import com.example.mission4.domain.member.exception.MemberException;
import com.example.mission4.domain.member.exception.code.MemberErrorCode;
import com.example.mission4.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    // 예시 요청 (멤버를 찾을 수 없음)
    @GetMapping("/test")
    public ApiResponse<String> test() {
        // throw new Exception("test");
        throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
    }
}
