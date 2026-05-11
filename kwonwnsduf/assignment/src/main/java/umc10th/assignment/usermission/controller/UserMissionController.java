package umc10th.assignment.usermission.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc10th.assignment.global.apiPayload.ApiResponse;
import umc10th.assignment.global.apiPayload.code.BaseSuccessCode;
import umc10th.assignment.usermission.dto.UserMissionRequestDto;
import umc10th.assignment.usermission.dto.UserMissionResponseDto;
import umc10th.assignment.usermission.exception.code.UserMissionSuccessCode;
import umc10th.assignment.usermission.service.UserMissionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserMissionController {
    private final UserMissionService userMissionService;

    // 내가 진행중인 미션 조회 - 오프셋 기반 페이지네이션
    @PostMapping("/members/me/missions/ongoing")
    public ApiResponse<UserMissionResponseDto.Pagination<UserMissionResponseDto.GetMyMission>> getMyOngoingMissions(
            @Valid @RequestBody UserMissionRequestDto.GetMyMission dto,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
        BaseSuccessCode code = UserMissionSuccessCode.OK;

        return ApiResponse.onSuccess(
                code,
                userMissionService.getMyOngoingMissions(dto, pageSize, pageNumber, sort)
        );
    }
}

