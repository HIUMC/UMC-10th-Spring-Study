package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.dto.MemberReqDTO;
import com.example.demo.domain.member.dto.MemberResDTO;
import com.example.demo.domain.member.dto.MyPageResponseDTO;
import com.example.demo.domain.member.exception.code.MemberSuccessCode;
import com.example.demo.domain.member.service.MemberService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Member", description = "멤버 관련 API")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/{memberId}/mypage")
    @Operation(summary = "마이 페이지 조회")
    public ResponseEntity<MyPageResponseDTO> getMyPage(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMyPage(memberId));
    }
}
