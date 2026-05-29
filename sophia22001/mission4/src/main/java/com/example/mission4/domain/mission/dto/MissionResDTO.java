package com.example.mission4.domain.mission.dto;

import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    @Builder
    public record GetMyMission(
            String storeName,
            Long point,
            String condition
    ){}

    @Builder
    public record MissionComplete(
            Long ownerId
    ){}

    @Builder
    public record GetStoreMission (
        Long missionId,
        Long point,
        String condition
    ){}

    // 페이지네이션 틀
    @Builder
    public record Pagination<T> (
        List<T> data,
        Integer pageNumber,
        Integer pageSize
    ){}
}
