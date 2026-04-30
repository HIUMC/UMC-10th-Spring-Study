package com.example.week4.domain.mission.service;

import com.example.week4.domain.mission.dto.MissionReqDTO;
import com.example.week4.domain.mission.dto.MissionResDTO;
import org.springframework.stereotype.Service;

@Service
public class MissionService {
    public MissionResDTO.MissionListResponse getStoreMissions(MissionReqDTO.StoreMissionListRequest dto) {
        return null;
    }

    public MissionResDTO.UserMissionListResponse getUserMissions(MissionReqDTO.UserMissionListRequest dto) {
        return null;
    }

    public MissionResDTO.UserMissionResponse challengeMission(MissionReqDTO.ChallengeMissionRequest dto) {
        return null;
    }
}
