package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.converter.MemberConverter;
import com.example.demo.domain.member.dto.MemberRequestDTO;
import com.example.demo.domain.member.dto.MemberResponseDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.service.MemberService;
import com.example.demo.domain.store.entity.Region;
import global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
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
    // 이번 주차는 Service/Repository 이전 단계라서 명세 확인용 샘플 응답을 바로 반환하도록 구성했다.

    private final MemberService memberService;

    @PostMapping("/members/signup")
    public ApiResponse<MemberResponseDTO.SignUpResultDTO> signUp(
            @RequestBody MemberRequestDTO.SignUpRequest request
    ) {
        MemberResponseDTO.SignUpResultDTO response = MemberResponseDTO.SignUpResultDTO.builder()
                .memberId(1L)
                .nickname(request.getNickname())
                .createdAt(LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/users/me")
    public ApiResponse<MemberResponseDTO.MeResultDTO> getHomeSummary(
            @ModelAttribute MemberRequestDTO.MeRequest request
    ) {
        Member member = memberService.getMemberProfile(request.getMemberId());

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
