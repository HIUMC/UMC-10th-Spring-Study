package com.example.umc10th.domain.user.controller;


import com.example.umc10th.domain.user.dto.MemberRequestDTO;
import com.example.umc10th.domain.user.dto.MemberResponseDTO;
import com.example.umc10th.domain.user.enums.MemberSuccessCode;
import com.example.umc10th.domain.user.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/notifications")
@RequiredArgsConstructor
public class MemberNotificationController {

private final MemberService memberService;


    //내 알림목록 조회
    @GetMapping("/notifications")
    public ApiResponse<MemberResponseDTO.GetMyNotifListInfo> getMyNotifListInfo(@RequestBody MemberRequestDTO.GetInfo dto) {

        return ApiResponse.onSuccess(MemberSuccessCode.NOTIFICATION_FOUND, memberService.getMyNotifListInfo(dto));

    }

    //알림 읽음 처리
    @PatchMapping("/notifications/{notificationId}/read")
    public ApiResponse<MemberResponseDTO.ReadNotifResult> readNotification(
            @PathVariable(name = "notificationId") Long notificationId,
            @RequestBody MemberRequestDTO.GetInfo dto) { // 어떤 유저의 알림인지 식별하기 위해 dto 유지

        return ApiResponse.onSuccess(MemberSuccessCode.NOTIFICATION_READ, memberService.readNotification(notificationId, dto));
    }
}
