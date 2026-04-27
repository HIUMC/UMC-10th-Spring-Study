package com.example.umc10th.domain.user.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResponseDTO {


    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {}

    //회원가입 응답
    @Builder
    public record JoinResult(
            Long memberId,
            LocalDateTime createdAt
    ){}

    //내 포인트 조회 응답
    @Builder
    public record GetMyPointInfo(
            Long memberId,
            String name,
            Integer point
          ) {}

    // 알림 목록 응답용
    @Builder
    public record GetMyNotifListInfo(
            List<NotifInfo> notifList
    ) {}

    // 알림 개별 정보
    @Builder
    public record NotifInfo(
            Long notificationId,
            String title,
            String body,
            Boolean isRead,
            LocalDateTime createdAt
    ) {}

    // 알림 읽음 처리 결과 응답용
    @Builder
    public record ReadNotifResult(
            Long notificationId,
            LocalDateTime readAt
    ) {}


}
