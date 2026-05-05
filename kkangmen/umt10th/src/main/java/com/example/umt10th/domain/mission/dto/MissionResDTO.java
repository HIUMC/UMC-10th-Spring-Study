package com.example.umt10th.domain.mission.dto;

import com.example.umt10th.domain.mission.enums.Status;
import lombok.Builder;

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
            String deadline
    ){}

    // 미션 성공 누르기
    @Builder
    public record MissionSuccessDto(
            LocalDateTime succeedAt
    ){ }
}
