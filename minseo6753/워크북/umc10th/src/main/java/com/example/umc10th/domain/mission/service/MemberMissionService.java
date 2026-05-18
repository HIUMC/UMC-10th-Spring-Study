package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MemberMissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO.Status;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.MemberMissionException;
import com.example.umc10th.domain.mission.exception.code.MemberMissionErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public MissionResDTO.Pagination<MissionResDTO.Info> getMemberMissions(Long memberId, MissionStatus status, Integer pageSize, Integer pageNumber) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize); // 한 번에 10개씩

        Page<MemberMission> memberMissionPage = memberMissionRepository.findAllByMemberAndStatusOrderByUpdatedAtDesc(
                member, status, pageRequest);

        return MemberMissionConverter.toPagination(
                memberMissionPage.map(MemberMissionConverter::toInfo).toList(),
                memberMissionPage.getNumber(),
                memberMissionPage.getSize()
        );
    }

    public MissionResDTO.Info updateMemberMission(Long missionId, Status request) {
        return null;
    }
}
