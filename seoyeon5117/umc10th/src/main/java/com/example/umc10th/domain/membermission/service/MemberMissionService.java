package com.example.umc10th.domain.membermission.service;

import com.example.umc10th.domain.membermission.dto.MemberMissionReqDTO;
import com.example.umc10th.domain.membermission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.membermission.enums.MemberMissionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberMissionService {

    public List<MemberMissionResDTO.GetMemberMission> getMemberMissions(Long memberId, MemberMissionStatus status) {
        return null;
    }

    public MemberMissionResDTO.UpdateMemberMissionStatus completeMission(Long missionId, MemberMissionReqDTO.CompleteMission dto) {
        return null;
    }
}
