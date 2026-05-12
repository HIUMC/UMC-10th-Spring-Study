package com.example.demo.domain.mission.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.exception.MemberException;
import com.example.demo.domain.member.exception.code.MemberErrorCode;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.mission.converter.MissionConverter;
import com.example.demo.domain.mission.dto.MissionResDTO;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.mapping.MemberMission;
import com.example.demo.domain.mission.enums.MissionStatus;
import com.example.demo.domain.mission.repository.MemberMissionRepository;
import com.example.demo.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    public MissionResDTO.MyMissionPreviewListDTO getMyMissions(Long memberId, MissionStatus status, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // Spring Data JPA의 페이징 기능
        // memberId, status 조건에 맞는 MemberMission 엔티티를 한 번에 다 가져오지 말고, page 단위로 끊어서 가져옴
        Page<MemberMission> memberMissionPage = memberMissionRepository
                .findAllByMemberAndMissionStatus(member, status, PageRequest.of(page, 10));

        return MissionConverter.toMyMissionPreviewListDTO(memberMissionPage);
    }

    public MissionResDTO.AvailableMissionListDTO getAvailableMissions(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Page<Mission> missionPage = missionRepository
                .findAvailableMissionsByRegion(member, member.getAddress(),PageRequest.of(page, 10));

        return MissionConverter.toAvailableMissionListDTO(missionPage);
    }
}