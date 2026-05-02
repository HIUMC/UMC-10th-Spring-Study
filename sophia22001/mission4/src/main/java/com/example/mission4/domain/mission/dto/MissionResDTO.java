package com.example.mission4.domain.mission.dto;

public class MissionResDTO {

    public record GetMissions(
            String storeName,
            Long point,
            Long minimumCost
    ){}

    public record MissionComplete(
            Long ownerId
    ){}
}
