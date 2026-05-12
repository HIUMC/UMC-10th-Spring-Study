package com.example.umc10th.domain.membermission.dto;

import com.example.umc10th.domain.membermission.enums.MemberMissionStatus;
import com.example.umc10th.global.enums.Food;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResDTO {

    @Builder
    public record CreateMemberMission(
            Long memberMissionId,
            LocalDateTime createdAt
    ) {
    }

    // 홈 화면 미션
    @Builder
    public record GetHomeMemberMission(
            Long storeId,
            String storeName,
            Food foodType,
            LocalDateTime dueDate,
            Integer missionPoint,
            String minPrice
    ) {
    }

    // 홈 화면 미션 목록
    @Builder
    public record GetHomeMemberMissions(
            Integer achievedCount,
            Integer totalCount,
            Page<GetHomeMemberMission> missions,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }

    // 미션
    @Builder
    public record GetMemberMission(
            Long storeId,
            String storeName,
            Integer missionPoint,
            MemberMissionStatus status
    ) {
    }

    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {}

    @Builder
    public record UpdateMemberMissionStatus(
            Long memberMissionId,
            MemberMissionStatus status,
            LocalDateTime updatedAt
    ) {
    }
}
