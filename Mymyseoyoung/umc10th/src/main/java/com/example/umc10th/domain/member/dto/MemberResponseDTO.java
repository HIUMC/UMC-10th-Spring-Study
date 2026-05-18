package com.example.umc10th.domain.member.dto;

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

    @Builder
    public record MemberProfileResponse (
        String nickname,
        String profileUrl,
        String email,
        String phoneNumber,
        boolean phoneVerified,
        int point
    ){}

    @Builder
    public record GetMyMissionListInfo(
            List<MissionInfo> missionList,
            boolean hasNext,
            int page
    ) {}

    @Builder
    public record MissionInfo(
            Long userMissionId,
            String storeName,
            String missionDescription,
            Integer rewardPoints,
            String status
    ) {}


    @Builder
    public record Pagination<T>(
            List<T> data,
            //다음 데이터가 있는지
            Integer pageNumber,
            Integer pageSize
    )
    {}

}
