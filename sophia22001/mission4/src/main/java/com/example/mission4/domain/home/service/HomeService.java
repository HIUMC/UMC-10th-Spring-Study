package com.example.mission4.domain.home.service;

import com.example.mission4.domain.home.converter.HomeConverter;
import com.example.mission4.domain.home.dto.HomeResDTO;
import com.example.mission4.domain.member.entity.Member;
import com.example.mission4.domain.member.exception.MemberException;
import com.example.mission4.domain.member.exception.code.MemberErrorCode;
import com.example.mission4.domain.member.repository.MemberRepository;
import com.example.mission4.domain.mission.entity.mapping.MemberMission;
import com.example.mission4.domain.mission.repository.MemberMissionRepository;
import com.example.mission4.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    public HomeResDTO.GetHome getHome(Long memberId) {

        // 1. 회원 조회 - memberRepository
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 2. 그 회원의 아직 완수하지 않은 미션 목록을 조회 - memberMissionRepository

        // 완료된 미션 수
        Integer completedMissionsCount = memberMissionRepository.countByMemberIdAndIsComplete(memberId, true);

        // 전체 미션 수
        Integer totalMissionsCount = memberMissionRepository.countByMemberId(memberId);

        // 남은 미션들
        List<MemberMission> missions = memberMissionRepository.findAllByMemberIdAndIsComplete(memberId, false);

        // 3. 컨버터로 ResDTO 형식 반환
        return HomeConverter.toGetHome(member, completedMissionsCount, totalMissionsCount, missions);


    }
}
