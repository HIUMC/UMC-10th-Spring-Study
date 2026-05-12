package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MissionResDTO {

    // 가게 내 미션 조회
    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String conditional
    ){}

    // 페이지네이션 툴
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}


}
