package com.example.umc10th.domain.mission.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionRequestDTO {

    public record CreateMission(

        LocalDate deadLine,
        Integer point,
        String conditional
    ){}
}
