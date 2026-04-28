package com.example.umc10th.domain.usermission.dto;

public class UserMissionReqDTO {

    public record GetUserMissionStatus(
            Long userMissionId
    ) {
    }

    public record CompleteMission(
            Long missionId,
            Boolean complete
    ) {
    }
}
