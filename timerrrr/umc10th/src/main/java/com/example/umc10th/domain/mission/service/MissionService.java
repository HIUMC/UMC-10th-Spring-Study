package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberAddressRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MemberAddressRepository memberAddressRepository;

    // 도전 가능 미션 목록 조회 - 회원이 선택한 주소 기준, 커서 페이징
    public List<MissionResDTO.MissionInfo> getAvailableMissions(Long memberId, MissionReqDTO.GetAvailableMissions dto) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(MissionErrorCode.NOT_FOUND));

        // 본인 주소가 아니거나 없으면 ADDRESS_NOT_FOUND
        memberAddressRepository.findByIdAndMemberId(dto.addressId(), memberId)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.ADDRESS_NOT_FOUND));

        LocalDate today = LocalDate.now();
        PageRequest pageable = PageRequest.of(0, dto.size());

        List<Mission> missions = missionRepository.findAvailableMissions(
                memberId,
                dto.addressId(),
                today,
                dto.lastDeadline(),   // 첫 페이지면 null
                dto.lastMissionId(),  // 첫 페이지면 null
                pageable
        );

        return MissionConverter.toMissionInfoList(missions, today);
    }

    // 내 미션 목록 조회 (진행중 / 진행 완료) - 커서 기반 페이징
    public List<MissionResDTO.MyMissionInfo> getMyMissions(Long memberId, MissionReqDTO.GetMyMissions dto) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(MissionErrorCode.NOT_FOUND));

        PageRequest pageable = PageRequest.of(0, dto.size());

        List<MemberMission> memberMissions = memberMissionRepository.findMyMissions(
                memberId,
                dto.status(),
                dto.lastDeadline(),   // 첫 페이지면 null
                dto.lastMissionId(),  // 첫 페이지면 null
                pageable
        );

        return MissionConverter.toMyMissionInfoList(memberMissions);
    }

    // 미션 성공 누르기
    @Transactional
    public void completeMission(Long memberId, Long missionId, MissionReqDTO.CompleteMission dto) {
        MemberMission memberMission = memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new ProjectException(MissionErrorCode.NOT_FOUND));

        memberMission.complete();
    }

}
