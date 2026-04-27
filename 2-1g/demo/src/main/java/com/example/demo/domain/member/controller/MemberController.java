package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.dto.MemberRequestDTO;
import com.example.demo.domain.member.dto.MemberResponseDTO;
import global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping
public class MemberController {
    // 이번 주차는 Service/Repository 이전 단계라서 명세 확인용 샘플 응답을 바로 반환하도록 구성했다.

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

    @GetMapping("/users/me/home-summary")
    public ApiResponse<MemberResponseDTO.HomeSummaryResultDTO> getHomeSummary(
            @ModelAttribute MemberRequestDTO.HomeSummaryRequest request
    ) {
        MemberResponseDTO.HomeSummaryResultDTO response = MemberResponseDTO.HomeSummaryResultDTO.builder()
                .regionId(request.getRegionId())
                .regionName("구로구")
                .point(1200)
                .completionCount(7)
                .build();

        return ApiResponse.onSuccess(response);
    }
}
