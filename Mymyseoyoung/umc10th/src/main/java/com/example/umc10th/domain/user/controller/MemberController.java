package com.example.umc10th.domain.user.controller;


import com.example.umc10th.domain.user.dto.MemberRequestDTO;
import com.example.umc10th.domain.user.dto.MemberResponseDTO;
import com.example.umc10th.domain.user.enums.MemberSuccessCode;
import com.example.umc10th.domain.user.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
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
    public ApiResponse<MemberResponseDTO.GetMyPointInfo> getMyPointInfo(@RequestBody MemberRequestDTO.GetInfo dto) {

        BaseSuccessCode code = MemberSuccessCode.MEMBER_POINT_FOUND;

        return ApiResponse.onSuccess(code,memberService.getMyPointInfo(dto));

    }

}
