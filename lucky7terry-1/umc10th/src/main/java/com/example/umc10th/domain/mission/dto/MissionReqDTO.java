package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MissionReqDTO {

//    public record MissionListRequest(
//            Boolean isCompleted,
//            Long cursor,
//            Integer size
//    ) {
//    }

    public record MissionCompleteRequest(
    ) {}

    // 가게 미션 생성
    public record CreateMission(

            @NotNull(message = "마감기한을 입력하세요.")
            LocalDate deadline,

            @NotNull(message = "포인트를 입력하세요.")
            Integer point,

            @NotBlank(message = "조건을 입력하세요.")
            String conditional
    ){}

    // 진행 중인 미션 조회 하기
    public record GetMission(
            @NotNull(message = "로그인 후 이용해주세요.")
            Long memberId
    ){}
}


