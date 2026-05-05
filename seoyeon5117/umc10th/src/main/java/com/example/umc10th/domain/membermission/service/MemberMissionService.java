package com.example.umc10th.domain.membermission.service;

import com.example.umc10th.domain.membermission.converter.MemberMissionConverter;
import com.example.umc10th.domain.membermission.dto.MemberMissionReqDTO;
import com.example.umc10th.domain.membermission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.membermission.entity.MemberMission;
import com.example.umc10th.domain.membermission.enums.MemberMissionStatus;
import com.example.umc10th.domain.membermission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;

    public Page<MemberMissionResDTO.GetMemberMission> getMemberMissionsByStatus(Long memberId, MemberMissionStatus status, Pageable pageable) {
        Page<MemberMission> missions = memberMissionRepository.findByMemberIdAndStatus(memberId, status, pageable);
        return missions.map(MemberMissionConverter::toGetMemberMission);
    }

    public MemberMissionResDTO.GetHomeMemberMissions getHomeMemberMissions(Long memberId, String address, Pageable pageable) {
        Page<MemberMission> missions = memberMissionRepository.findByMemberIdAndStatusAndAddress(memberId, MemberMissionStatus.IN_PROGRESS, address, pageable);
        Integer achievedCount = memberMissionRepository.countByMemberIdAndStatus(memberId, MemberMissionStatus.COMPLETED);
        Integer totalCount = memberMissionRepository.countByMemberId(memberId);

        return MemberMissionConverter.toGetHomeMemberMissions(missions, achievedCount, totalCount);
    }

    public MemberMissionResDTO.UpdateMemberMissionStatus completeMission(Long missionId, MemberMissionReqDTO.CompleteMission dto) {
        return null;
    }
}
