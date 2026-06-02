package com.example.umc10th.domain.member.controller;


import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /*@PostMapping("/me")
    public ApiResponse<MemberResponseDTO.GetInfo> getInfo(@RequestBody MemberRequestDTO.GetInfo dto) {
        BaseSuccessCode code = MemberSuccessCode.OK;

        return ApiResponse.onSuccess(code, userService.getInfo(dto));
    }

     */

    //내 포인트 조회
    @GetMapping("/points")
    public ApiResponse<MemberResponseDTO.GetMyPointInfo> getMyPointInfo(@AuthenticationPrincipal AuthMember authMember){

        BaseSuccessCode code = MemberSuccessCode.MEMBER_POINT_FOUND;

        return ApiResponse.onSuccess(code,memberService.getMyPointInfo(authMember.getMember().getId()));


    }

    //마이페이지 전체 정보 조회
    @GetMapping("/{memberId}/my-page")
    public ApiResponse<MemberResponseDTO.MemberProfileResponse> getMyPage(@AuthenticationPrincipal AuthMember authMember) {
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_POINT_FOUND,memberService.getMyPage(authMember.getMember().getId()));
    }

}
