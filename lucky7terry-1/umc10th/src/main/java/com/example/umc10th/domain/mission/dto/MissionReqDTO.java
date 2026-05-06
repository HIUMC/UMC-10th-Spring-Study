package com.example.umc10th.domain.mission.dto;

public class MissionReqDTO {

    public record MissionListRequest(
            Long memberId,
            Boolean isCompleted,
            Long cursor,
            Integer size
    ) {
    }

    public record MissionCompleteRequest(
            Long memberId
    ) {
    }
}


