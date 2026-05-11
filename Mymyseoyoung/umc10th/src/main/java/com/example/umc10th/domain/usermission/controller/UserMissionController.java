package com.example.umc10th.domain.usermission.controller;


import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.domain.usermission.dto.UserMissionRequestDTO;
import com.example.umc10th.domain.usermission.dto.UserMissionResponseDTO;
import com.example.umc10th.domain.usermission.entity.UserMission;
import com.example.umc10th.domain.usermission.enums.UserMissionStatus;
import com.example.umc10th.domain.usermission.exception.code.UserMissionSuccessCode;
import com.example.umc10th.domain.usermission.service.UserMissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-missions")
@RequiredArgsConstructor
public class UserMissionController {
    private final UserMissionService userMissionService;
    private final MissionService missionService;
    private final MemberService memberService;

    //미션 도전 신청
    @PostMapping("/{missionId}")
    public ApiResponse<UserMissionResponseDTO.ChallengeResultDTO> challengeMission(
            @PathVariable(name = "missionId") Long missionId,
            @RequestBody MemberRequestDTO.GetInfo userDto) {

      //Mission_challenged 이거 successcode 추가

        return ApiResponse.onSuccess(null, null);
    }

    //미션 성공 처리
    @PatchMapping("/{missionId}")
    public ApiResponse<UserMissionResponseDTO.MissionCompleteResultDTO> completeMission(
            @PathVariable(name = "missionId") Long missionId,
            @RequestBody MemberRequestDTO.GetInfo userDto) {

        // TODO: UserMissionSuccessCode.MISSION_COMPLETED 추가 후 사용
        return ApiResponse.onSuccess(null, null);
    }

    //내 미션 조회
    @GetMapping("/{memberId}/missions")
    public ApiResponse<MemberResponseDTO.GetMyMissionListInfo> getMyMissions(
            @PathVariable Long memberId,
            @RequestParam UserMissionStatus status,
            @RequestParam(defaultValue = "0") int page) {

        MemberResponseDTO.GetMyMissionListInfo response = userMissionService.getMyMissions(memberId, status, page);

        return ApiResponse.onSuccess(UserMissionSuccessCode.MISSION_LIST_FOUND,response);
    }

    //내가 진행중인 미션 조회하기 -> RESTful api에 맞지 않음
    //Page 감쌀 필요가 없음 -> GetMyMissionList에 page 정보 내장 !
    @PostMapping
    public ApiResponse<MemberResponseDTO.Pagination<MemberResponseDTO.MissionInfo>> getMyOngoingMissions(
            @RequestBody @Valid UserMissionRequestDTO.getMemberInfo request)
    {
        MemberResponseDTO.Pagination<MemberResponseDTO.MissionInfo> response = userMissionService.getMyOngoingMissions(
                request.memberId(),
                UserMissionStatus.CHALLENGING,
                request.page()
        );

        return ApiResponse.onSuccess(UserMissionSuccessCode.MISSION_LIST_FOUND,response);
    }


}
