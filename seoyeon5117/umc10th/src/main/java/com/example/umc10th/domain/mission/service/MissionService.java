package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    public MissionResDTO.CreateMission createMission(MissionReqDTO.CreateMission dto) {
        return null;
    }

    public MissionResDTO.GetMission getMission(Long missionId) {
        return null;
    }

    public List<MissionResDTO.GetMission> getMissions() {
        return null;
    }

    public MissionResDTO.UpdateMission updateMission(Long missionId, MissionReqDTO.UpdateMission dto) {
        return null;
    }

    public void deleteMission(Long missionId) {
    }
}
