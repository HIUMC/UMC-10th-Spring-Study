package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {


    @Builder
    public record MissionDTO(
            Long missionId,
            String content,
            Long reward,
            LocalDate deadline
    ) {}

    @Builder
    public record MissionListDTO(
            List<MissionDTO> missionList,
            Long lastMissionId,
            Boolean hasNext
    ) {}


    //가게 내 미션 조회
    @Builder
    public record GetMission(
            Long missionId,
            Long point,
            String conditional
    ){}


    //페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {}




}
