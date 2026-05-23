package com.example.mission4.domain.mission.dto;

import lombok.Builder;

public class MissionResDTO {

    @Builder
    public record GetMissions(
            String storeName,
            Long point,
            String condition
    ){}

    public record MissionComplete(
            Long ownerId
    ){}
}
