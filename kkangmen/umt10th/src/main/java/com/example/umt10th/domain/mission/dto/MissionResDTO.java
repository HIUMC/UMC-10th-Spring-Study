package com.example.umt10th.domain.mission.dto;

import com.example.umt10th.domain.mission.enums.Status;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    // 미션 조회
    @Builder
    public record MissionListDto(
            List<MissionDetailDto> missionList
    ){}

    @Builder
    public record MissionDetailDto(
            Long missionId,
            String storeName,
            Integer point,
            Status status,
            String conditional,
            LocalDate deadline
    ){}

    // 미션 성공 누르기
    @Builder
    public record MissionSuccessDto(
            LocalDateTime succeedAt
    ){ }

    // 가게 내 미션, 진행 중인 미션 조회
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
