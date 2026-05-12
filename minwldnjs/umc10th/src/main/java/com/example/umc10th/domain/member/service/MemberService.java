package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MemberResDTO.MyPageDTO getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        return MemberResDTO.MyPageDTO.builder()
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }

    public MissionResDTO.MissionListDTO getMyMissions(Long memberId, String status, int page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        Boolean complete = status.equals("complete");
        Page<MemberMission> memberMissions = memberMissionRepository
                .findByMemberAndComplete(member, complete, PageRequest.of(page - 1, 10));

        List<MissionResDTO.MissionDetailDTO> missionList = memberMissions.stream()
                .map(mm -> MissionResDTO.MissionDetailDTO.builder()
                        .missionId(mm.getMission().getId())
                        .title(mm.getMission().getMissionContent())
                        .reward(mm.getMission().getPoint())
                        .status(status)
                        .build())
                .collect(Collectors.toList());

        return MissionResDTO.MissionListDTO.builder()
                .missionList(missionList)
                .build();
    }
}