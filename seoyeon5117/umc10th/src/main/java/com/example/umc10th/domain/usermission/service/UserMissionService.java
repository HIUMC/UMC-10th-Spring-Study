package com.example.umc10th.domain.usermission.service;

import com.example.umc10th.domain.usermission.dto.UserMissionReqDTO;
import com.example.umc10th.domain.usermission.dto.UserMissionResDTO;
import com.example.umc10th.domain.usermission.enums.UserMissionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMissionService {

    public List<UserMissionResDTO.GetUserMission> getUserMissions(Long memberId, UserMissionStatus status) {
        return null;
    }

    public UserMissionResDTO.UpdateUserMissionStatus completeMission(Long missionId, UserMissionReqDTO.CompleteMission dto) {
        return null;
    }
}
