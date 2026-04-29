package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.stereotype.Service;

@Service
public class MissionService {
    public MissionResDTO.InfoList getMissions(String eupMyeonDong) {
        return null;
    }

    public MissionResDTO.InfoList getMemberMissions(MissionStatus status) {
        return null;
    }

    public MissionResDTO.Info updateMemberMission(MissionReqDTO.Status request) {
        return null;
    }
}
