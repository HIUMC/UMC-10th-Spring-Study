package com.example.umc10th.domain.mission.dto;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class MissionReqDTO {


    public record MissionPageDTO(

            @NotNull(message = "로그인 ID는 필수입니다.")
            @Positive(message = "올바른 ID 형식이 아닙니다.")
            Long loginId

    ){}
    public record CreateMission(
            LocalDate deadline,
            Integer point,
            String conditional
    ){}
}
