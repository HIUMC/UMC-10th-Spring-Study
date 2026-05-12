package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MissionResDTO.MissionListDTO getMissionList(
            MissionReqDTO.MissionListRequest dto
    ) {
        Long memberId = dto.memberId();
        int pageSize = normalizeSize(dto.size());
        Pageable pageable = PageRequest.of(0, pageSize + 1);

        List<MemberMission> rows = memberMissionRepository.findMemberMissionsByStatus(
                memberId,
                dto.isCompleted(),
                dto.cursor() == null ? 0L : dto.cursor(),
                pageable
        );

        boolean hasNext = rows.size() > pageSize;
        List<MemberMission> content = hasNext ? rows.subList(0, pageSize) : rows;
        Long nextCursor = content.isEmpty() ? null : content.get(content.size() - 1).getId();

        return MissionConverter.toMissionListDTO(content, hasNext, nextCursor);
    }

    @Transactional
    public MissionResDTO.MissionCompleteDTO completeMission(
            Long missionId,
            MissionReqDTO.MissionCompleteRequest dto
    ) {
        Long memberId = dto.memberId();

        Member member = memberRepository.findByIdAndDeletedAtIsNull(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        MemberMission memberMission = memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId)
                .orElseGet(() -> memberMissionRepository.save(MemberMission.builder()
                        .member(member)
                        .mission(mission)
                        .isCompleted(false)
                        .content("방문하기")
                        .build()));

        memberMission.complete(memberMission.getContent() == null ? "방문하기" : memberMission.getContent());
        member.addPoint(mission.getPoint());

        return MissionConverter.toMissionCompleteDTO(memberMission);
    }

    private int normalizeSize(Integer size) {
        if (size == null || size < 1) {
            return 10;
        }

        return Math.min(size, 20);
    }
}
