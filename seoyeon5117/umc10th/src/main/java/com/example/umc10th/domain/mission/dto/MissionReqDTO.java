package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class MissionReqDTO {

    public record CreateMission(
            @NotNull(message = "조건은 빈칸일 수 없습니다.")
            String minPrice,
            @NotNull(message = "미션 성공 포인트는 필수입니다.")
            Integer point,
            @NotNull(message = "마감 기한은 필수입니다.")
            LocalDateTime due_date
    ) {
    }

    public record UpdateMission(
            String minPrice,
            Integer point,
            LocalDateTime due_date
    ) {
    }
}
