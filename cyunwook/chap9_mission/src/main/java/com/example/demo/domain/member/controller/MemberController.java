package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.dto.MemberReqDTO;
import com.example.demo.domain.member.dto.MemberResDTO;
import com.example.demo.domain.member.dto.MyPageResponseDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.exception.code.MemberSuccessCode;
import com.example.demo.domain.member.service.MemberService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.BaseSuccessCode;
import com.example.demo.global.security.entity.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PostMapping("/members")
    @Operation(summary = "멤버 생성")
    public ApiResponse<Long> createMember(@RequestBody @Valid MemberReqDTO.CreateMember request) {
        Member member = memberService.createMember(request);
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, member.getId());
    }

    // 마이페이지
    @GetMapping("/v2/users/me")
    @Operation(summary = "마이페이지 조회 (JWT)")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @AuthenticationPrincipal AuthMember member
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(member));
    }
}