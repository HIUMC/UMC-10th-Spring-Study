package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class MissionService {
    public MissionResDTO.MissionListDTO getMissionList(Integer isCompleted, String token) {
        // 미션 목록 조회
        return null;
    }

    public MissionResDTO.MissionCompleteDTO completeMission(Long missionId, String token, MissionReqDTO.MissionCompleteDTO dto) {
        // 미션 성공 처리
        return null;
    }
}
