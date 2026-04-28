package com.example.umc10th.domain.notification.controller;

import com.example.umc10th.domain.notification.dto.NotificationResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NotificationController {

    // 알람 목록 조회
    @GetMapping("/members/me/notifications")
    public ApiResponse<NotificationResDTO.NotificationListDTO> getNotifications(
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

}
