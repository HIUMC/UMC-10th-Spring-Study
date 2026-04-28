package com.example.umc10th.domain.usermission.service;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.usermission.dto.UserMissionReqDTO;
import com.example.umc10th.domain.usermission.dto.UserMissionResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMissionService {

    public List<UserMissionResDTO.GetUserMission> getUserMissions(Long memberId, MissionStatus status) {
        return null;
    }

    public UserMissionResDTO.UpdateUserMissionStatus completeMission(Long missionId, UserMissionReqDTO.CompleteMission dto) {
        return null;
    }
}
