package com.example.mission4.domain.home.dto;

import com.example.mission4.global.common.enums.FoodName;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class HomeResDTO {

    @Builder
    public record GetHome(
            String location,
            Integer completedMissionsCount,
            Integer totalMissionsCount,
            Long achieving10MissionsPoint,
            List<HomeMissionDTO> notCompletedMissions

    ){}

    @Builder
    public record HomeMissionDTO(
            String storeName,
            FoodName storeCategory,
            String condition,
            Long point,
            LocalDate deadline
    ){}
}
