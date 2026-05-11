package com.example.umc10th.domain.mission.controller;


import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/missions")
public class MissionController {

    private final MissionService missionService;


    //내가 진행한 미션 보기
    @GetMapping("/my")
    public ApiResponse<MissionResDTO.MissionListDTO> getMissions(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) String cursor,
            @RequestParam Long memberId
    ){


        BaseSuccessCode code = MissionSuccessCode.OK;

        return ApiResponse.onSuccess(code, missionService.getMyMissions(memberId, status, cursor));
    }

}
