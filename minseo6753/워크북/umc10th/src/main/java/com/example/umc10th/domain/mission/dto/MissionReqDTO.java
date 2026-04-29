package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;

public class MissionReqDTO {

    public record Status(
            MissionStatus missionStatus
    ) {
    }
}
