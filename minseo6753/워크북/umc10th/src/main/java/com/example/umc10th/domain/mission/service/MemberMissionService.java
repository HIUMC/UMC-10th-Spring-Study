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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public MissionResDTO.InfoSlice getMemberMissions(Long memberId, MissionStatus status, Long cursor) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(0, 10); // 한 번에 10개씩
        Slice<MemberMission> missionSlice;

        if (cursor == null) {
            missionSlice = memberMissionRepository.findFirstPage(member, status, pageRequest);
        } else {

            MemberMission lastFound = memberMissionRepository.findById(cursor)
                    .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.NOT_FOUND));

            missionSlice = memberMissionRepository.findNextPage(member,
                    status, lastFound.getUpdatedAt(), lastFound.getId(), pageRequest);
        }

        return MemberMissionConverter.toInfoSlice(missionSlice);
    }

    public MissionResDTO.Info updateMemberMission(Long missionId, Status request) {
        return null;
    }
}
