package com.example.umc10th.domain.membermission.dto;

public class MemberMissionReqDTO {

    public record GetMemberMissionStatus(
            Long memberMissionId
    ) {
    }

    public record CompleteMission(
            Long missionId,
            Boolean complete
    ) {
    }
}
