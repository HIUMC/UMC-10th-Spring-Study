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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    // 도전 가능 미션 목록 조회 - 회원이 선택한 주소 기준, 오프셋 기반 페이징
    public MissionResDTO.Pagination<MissionResDTO.MissionInfo> getAvailableMissions(
            Long memberId, Long addressId, Integer pageSize, Integer pageNumber, String sort
    ) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(MissionErrorCode.NOT_FOUND));

        // 본인 주소가 아니거나 없으면 ADDRESS_NOT_FOUND
        memberAddressRepository.findByIdAndMemberId(addressId, memberId)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.ADDRESS_NOT_FOUND));

        LocalDate today = LocalDate.now();

        // 정렬 정보 생성
        Sort sortInfo;
        if (sort != null) {
            sortInfo = Sort.by(sort).descending();
        } else {
            sortInfo = Sort.by("deadline").ascending();;  //마감임박순
        }

        PageRequest pageable = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<Mission> missionPage = missionRepository.findAvailableMissions(
                memberId,
                addressId,
                today,
                pageable
        );

        return MissionConverter.toPagination(
                missionPage.map(m -> MissionConverter.toMissionInfo(m, today)).toList(),
                missionPage.getSize(),
                missionPage.getNumber()
        );

    }

    // 내 미션 목록 조회 (진행중 / 진행 완료) - 오프셋 기반 페이징
    public MissionResDTO.Pagination<MissionResDTO.MyMissionInfo> getMyMissions(
            Long memberId, String status, Integer pageSize, Integer pageNumber, String sort
            ) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(MissionErrorCode.NOT_FOUND));

        // 정렬 정보 생성
        Sort sortInfo;
        if (sort != null) {
            sortInfo = Sort.by(sort).descending();
        } else {
            sortInfo = Sort.by("id").descending();
        }

        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        // status 문자열을 boolean으로 매핑
        Boolean isComplete = "COMPLETE".equalsIgnoreCase(status);

        // 내 미션 목록 조회
        Page<MemberMission> memberMissionPage = memberMissionRepository
                .findAllByMember_IdAndMissionComplete(memberId, isComplete, pageRequest);

        // 미션들 응답 DTO로 포장하기
        return MissionConverter.toPagination(
                memberMissionPage.map(MissionConverter::toMyMissionInfo).toList(),
                memberMissionPage.getSize(),
                memberMissionPage.getNumber()
        );
    }

    // 미션 성공 누르기
    @Transactional
    public void completeMission(Long memberId, Long missionId, MissionReqDTO.CompleteMission dto) {
        MemberMission memberMission = memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new ProjectException(MissionErrorCode.NOT_FOUND));

        memberMission.complete();
    }

}
