package com.example.umc10th.domain.mission.service;


import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MemberMissionStatus;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 진행 가능 미션 반환
    public List<Mission> getAvailableMissions(Long regionId, MissionStatus status, Long cursor, Integer size) {
        return missionRepository.findAvailableMissions(regionId, status, cursor, PageRequest.of(0, size + 1));
    }

    // 내 미션 반환
    public List<MemberMission> getMyMissions(Long memberId, MemberMissionStatus status, Long cursor, Integer size) {
        return memberMissionRepository.findMyMissions(memberId, status, cursor, PageRequest.of(0, size + 1));
    }

    // 내 미션 반환 (오프셋 페이징)
    public MissionResDTO.Pagination<MissionResDTO.MyMissionDTO> getMyMissionsByOffset(Long memberId, MemberMissionStatus status, Integer page, Integer size) {
        Page<MemberMission> memberMissionPage = memberMissionRepository.findAllByMemberIdAndStatus(memberId, status, PageRequest.of(page, size));
        return MissionConverter.toMyMissionPaginationDTO(memberMissionPage);
    }
}