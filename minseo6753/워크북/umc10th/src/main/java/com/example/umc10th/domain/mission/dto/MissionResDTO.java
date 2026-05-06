package com.example.umc10th.domain.mission.dto;

import java.util.List;
import lombok.Builder;

public class MissionResDTO {

    @Builder
    public record Info(
            String restaurantName,
            String category,
            Long daysLeft,
            String content,
            Integer point
    ) {
    }

    @Builder
    public record InfoList(
            List<Info> infos
    ) {
    }

    @Builder
    public record InfoSlice(
            InfoList infoList,
            Long nextCursor,
            Boolean hasNext
    ) {
    }
}
