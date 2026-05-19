package com.example.demo.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class MissionReqDTO {

    public record  CreateMission(
            @NotNull(message = "마감기한은 필수입니다")
            LocalDate deadline,
            @NotNull(message = "미션 성공 포인트는 필수입니다")
            Integer point,
            @NotBlank(message = "조건은 빈칸일 수 없습니다.")
            String conditional
    ){}
    //진행중 미션 조회 요청 dto
    @Getter
    public static class GetMyMission{
        @NotNull
        private Long memberId;
    }
}
