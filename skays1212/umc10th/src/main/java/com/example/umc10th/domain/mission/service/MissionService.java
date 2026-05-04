package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MissionResDTO.RegionMissionResDTO getMissionsByRegion(String region, Long memberId, int page, int size) {
        Address address;
        try {
            address = Address.valueOf(region);
        } catch (IllegalArgumentException e) {
            throw new MissionException(MissionErrorCode.ADDRESS_NOT_FOUND);
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Mission> missionPage = missionRepository.findAvailableMissions(address, memberId, pageable);

        return MissionConverter.toRegionMissionResDTO(missionPage);
    }

    @Transactional(readOnly = true)
    public MissionResDTO.MyMissionResDTO getMyMissions(Long memberId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MemberMission> memberMissionPage = memberMissionRepository.findByMemberId(memberId, pageable);

        return MissionConverter.toMyMissionResDTO(memberMissionPage);
    }

    public MissionResDTO.MissionChallengeResDTO challengeMission(Long missionId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        if (memberMissionRepository.existsByMember_IdAndMission_Id(memberId, missionId)) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_CHALLENGING);
        }

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();

        MemberMission saved = memberMissionRepository.save(memberMission);

        return MissionConverter.toMissionChallengeResDTO(saved);
    }
}
