package umc10th.assignment.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import umc10th.assignment.global.apiPayload.ApiResponse;
import umc10th.assignment.global.apiPayload.code.BaseSuccessCode;
import umc10th.assignment.mission.dto.MissionResponseDto;
import umc10th.assignment.mission.exception.code.MissionSuccessCode;
import umc10th.assignment.mission.service.MissionService;
import umc10th.assignment.usermission.entity.MissionStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    // 홈 화면: 선택한 지역에서 도전 가능한 미션 목록 조회
    @GetMapping
    public ApiResponse<MissionResponseDto.MissionPreviewListDTO> getMissionsByRegion(
            @RequestParam Long regionId,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        BaseSuccessCode code = MissionSuccessCode.GET_MISSIONS;

        MissionResponseDto.MissionPreviewListDTO response =
                missionService.getMissionsByRegion(
                        regionId,
                        PageRequest.of(page, 10)
                );

        return ApiResponse.onSuccess(code, response);
    }

    // 내가 진행중/진행완료한 미션 조회
    @GetMapping("/my")
    public ApiResponse<MissionResponseDto.MissionPreviewListDTO> getMyMissions(
            @RequestParam Long memberId,
            @RequestParam MissionStatus status,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        BaseSuccessCode code = MissionSuccessCode.GET_MISSIONS;

        MissionResponseDto.MissionPreviewListDTO response =
                missionService.getMyMissions(
                        memberId,
                        status,
                        PageRequest.of(page, 10)
                );

        return ApiResponse.onSuccess(code, response);
    }
}
