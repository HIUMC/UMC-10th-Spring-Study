package com.example.mission4.domain.mission.dto;

public class MissionReqDTO {
    public record GetMissions (
            Long memberId
    ){}

    public record MissionComplete (
            Long memberId
    ){}
}
