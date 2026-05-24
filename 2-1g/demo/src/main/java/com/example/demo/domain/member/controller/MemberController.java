package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.converter.MemberConverter;
import com.example.demo.domain.member.dto.MemberRequestDTO;
import com.example.demo.domain.member.dto.MemberResponseDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.service.MemberService;
import com.example.demo.domain.store.entity.Region;
import global.apiPayload.ApiResponse;
import global.security.AuthMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members/signup")
    public ApiResponse<MemberResponseDTO.SignUpResultDTO> signUp(
            @Valid @RequestBody MemberRequestDTO.SignUpRequest request
    ) {
        MemberResponseDTO.SignUpResultDTO response = MemberResponseDTO.SignUpResultDTO.builder()
                .memberId(1L)
                .nickname(request.getNickname())
                .createdAt(LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess(response);
    }

    // 마이페이지 조회
    // Member로 조회하는 것이 아닌 authMember를 통해 토큰에서 Id를 꺼내주기
    @GetMapping("/users/me")
    public ApiResponse<MemberResponseDTO.MeResultDTO> getHomeSummary(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        Member member = memberService.getMemberProfile(authMember.getMemberId());

        MemberResponseDTO.MeResultDTO response = MemberConverter.toHomeSummaryResultDTO(member);

        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/users/me/home-summary")
    public ApiResponse<MemberResponseDTO.HomeSummaryResultDTO> getHomeSummary(
            @ModelAttribute MemberRequestDTO.HomeSummaryRequest request
    ) {
        Member member = memberService.getMemberProfile(request.getMemberId());
        Region region = memberService.getHomeSummaryRegion(request.getRegionId());

        MemberResponseDTO.HomeSummaryResultDTO response = MemberConverter.toHomeSummaryResultDTO(member, region);

        return ApiResponse.onSuccess(response);
    }
}
