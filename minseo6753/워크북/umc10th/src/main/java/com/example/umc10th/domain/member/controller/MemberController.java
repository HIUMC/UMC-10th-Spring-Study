package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/eup-myeon-dong")
    public ApiResponse<MemberResDTO.Location> getLocation(
            //토큰
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberService.getLocation()
        );
    }

    @PatchMapping("/eup-myeon-dong")
    public ApiResponse<MemberResDTO.Location> updateLocation(
            @RequestBody MemberReqDTO.Location request
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberService.updateLocation(request)
        );
    }

    @GetMapping("/mission-count")
    public ApiResponse<MemberResDTO.MissionCount> getMissionCount(
            //토큰
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberService.getMissionCount()
        );
    }

}
