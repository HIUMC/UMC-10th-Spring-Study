package com.example.demo.domain.mission.service;

import com.example.demo.domain.mission.entity.MemberMission;
import com.example.demo.domain.mission.enums.MissionStatus;
import com.example.demo.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MemberMissionRepository memberMissionRepository;

    public Page<MemberMission> getMemberMissions(Long memberId, MissionStatus status, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return memberMissionRepository.findAllByMemberIdAndStatus(memberId, status, pageRequest);
    }
}
