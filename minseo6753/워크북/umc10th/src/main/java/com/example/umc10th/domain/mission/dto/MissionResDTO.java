package com.example.umc10th.domain.mission.dto;

import java.util.List;

public class MissionResDTO {

    public record Info(
            String restaurantName,
            String category,
            Integer daysLeft,
            String content,
            Integer point
    ) {
    }

    public record InfoList(
            List<Info> infoList
    ) {
    }
}
