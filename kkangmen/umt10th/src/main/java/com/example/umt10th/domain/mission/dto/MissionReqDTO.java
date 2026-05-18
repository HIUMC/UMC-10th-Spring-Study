package com.example.umt10th.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MissionReqDTO {

    // 가게 미션 생성
    public record CreateMission(

            @NotNull(message = "마감기한은 필수입니다.")
            LocalDate deadLine,

            @NotNull(message = "미션 성공 포인트는 필수입니다.")
            Integer point,

            @NotBlank(message = "조건은 빈칸일 수 없습니다.")
            String conditional
    ){}

    // 진행 중인 미션 조회 하기
    public record GetMission(

            @NotNull(message = "로그인 후 이용해주세요.")
            Long memberId
    ){}
}
