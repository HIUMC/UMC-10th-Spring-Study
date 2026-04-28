package umc10th.assignment.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc10th.assignment.global.apiPayload.ApiResponse;
import umc10th.assignment.global.apiPayload.code.BaseSuccessCode;
import umc10th.assignment.mission.dto.MissionResponseDto;
import umc10th.assignment.mission.exception.code.MissionSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {
    @GetMapping
    public ApiResponse<MissionResponseDto.MissionList> getMissions(
            @RequestParam(required = false) String status
    ) {
        BaseSuccessCode code = MissionSuccessCode.GET_MISSIONS;

        MissionResponseDto.MissionInfo mission = MissionResponseDto.MissionInfo.builder()
                .missionId(1L)
                .title("가게 리뷰 작성하기")
                .content("방문한 가게에 리뷰를 작성합니다.")
                .reward(500)
                .status(status == null ? "ONGOING" : status)
                .build();

        MissionResponseDto.MissionList response = MissionResponseDto.MissionList.builder()
                .missions(List.of(mission))
                .build();

        return ApiResponse.onSuccess(code, response);
    }

    // 미션 상세 조회
    @GetMapping("/{missionId}")
    public ApiResponse<MissionResponseDto.MissionDetail> getMissionDetail(
            @PathVariable Long missionId
    ) {
        BaseSuccessCode code = MissionSuccessCode.GET_MISSION_DETAIL;

        MissionResponseDto.MissionDetail response = MissionResponseDto.MissionDetail.builder()
                .missionId(missionId)
                .title("가게 리뷰 작성하기")
                .content("방문한 가게에 리뷰를 작성하면 포인트를 받을 수 있습니다.")
                .reward(500)
                .status("ONGOING")
                .build();

        return ApiResponse.onSuccess(code, response);
    }

    // 미션 성공 처리
    @PatchMapping("/{missionId}/complete")
    public ApiResponse<MissionResponseDto.CompleteMission> completeMission(
            @PathVariable Long missionId
    ) {
        BaseSuccessCode code = MissionSuccessCode.COMPLETE_MISSION;

        MissionResponseDto.CompleteMission response = MissionResponseDto.CompleteMission.builder()
                .missionId(missionId)
                .status("COMPLETED")
                .build();

        return ApiResponse.onSuccess(code, response);
    }
}
