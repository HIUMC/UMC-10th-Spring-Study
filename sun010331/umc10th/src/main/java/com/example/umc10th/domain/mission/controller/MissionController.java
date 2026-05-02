package com.example.umc10th.domain.mission.controller;


import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/missions")
public class MissionController {


    @GetMapping()
    public ApiResponse<MissionResDTO.MissionListDTO> getMissions(
            @RequestParam(name = "status") String status,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "lastMissionId", required = false) Long lastMissionId
    ){
        //임시 데이터
        MissionResDTO.MissionListDTO result = MissionResDTO.MissionListDTO.builder()
                .missionList(null) //미션은 없다고 가정
                .lastMissionId(1L)
                .hasNext(false)
                .build();


        return ApiResponse.onSuccess("미션 목록 조회 성공.", result);
    }

}
