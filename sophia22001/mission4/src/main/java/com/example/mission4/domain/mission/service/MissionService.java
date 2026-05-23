package com.example.mission4.domain.mission.service;

import com.example.mission4.domain.mission.converter.MemberMissionConverter;
import com.example.mission4.domain.mission.converter.MissionConverter;
import com.example.mission4.domain.mission.dto.MissionReqDTO;
import com.example.mission4.domain.mission.dto.MissionResDTO;
import com.example.mission4.domain.mission.entity.mapping.MemberMission;
import com.example.mission4.domain.mission.repository.MemberMissionRepository;
import com.example.mission4.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    public List<MissionResDTO.GetMissions> getMissions(Boolean isCompleted, Long memberId) {

        // 1. 해당 유저의 미션 중 완료 여부(isComplete)가 일치하는 것들을 찾음
        List<MemberMission> memberMissions = memberMissionRepository.findAllByMemberIdAndIsComplete(memberId, isCompleted);

        // 2. 엔티티 리스트를 DTO 리스트로 변환 (Converter 활용)
        return MemberMissionConverter.toGetMissionsList(memberMissions);
    }

    public MissionResDTO.MissionComplete missionComplete(Long storeId, Long missionId, Long memberId) {

        // 유저의 포인트에 성공한 미션의 포인트 저장
        return null;
    }

}
