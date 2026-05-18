package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    // 미션 조회
    @Builder
    public record MissionListDTO(
            List<MissionPreviewDTO> missionList
    ) { }

    @Builder
    public record MissionPreviewDTO(
            Long memberMissionId,
            Long missionId,
            String storeName,
            Integer point,
            String status,
            String content,
            LocalDate deadline
    ) { }

    // 미션 완료 누르기
    @Builder
    public record MissionCompleteDTO(
            LocalDateTime completedAt
    ) { }

    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String conditional
    ){}

    // 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext, // 다음 데이터가 존재하는지
            String nextCursor, // 다음 커서의 값
            Integer pageSize // 불러온 데이터 수
    ){}
}

