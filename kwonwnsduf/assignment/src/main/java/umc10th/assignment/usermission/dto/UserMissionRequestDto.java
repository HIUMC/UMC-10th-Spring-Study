package umc10th.assignment.usermission.dto;

import jakarta.validation.constraints.NotNull;

public class UserMissionRequestDto {
    public record GetMyMission(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId
    ) {
    }
}

