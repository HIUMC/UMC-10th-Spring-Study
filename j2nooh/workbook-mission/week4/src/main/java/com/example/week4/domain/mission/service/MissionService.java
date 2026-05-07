package com.example.week4.domain.mission.service;

import com.example.week4.domain.mission.converter.MissionConverter;
import com.example.week4.domain.mission.dto.MissionReqDTO;
import com.example.week4.domain.mission.dto.MissionResDTO;
import com.example.week4.domain.mission.entity.Mission;
import com.example.week4.domain.mission.entity.mapping.UserMission;
import com.example.week4.domain.mission.enums.MissionStatus;
import com.example.week4.domain.mission.repository.MissionRepository;
import com.example.week4.domain.mission.repository.UserMissionRepository;
import com.example.week4.domain.user.entity.User;
import com.example.week4.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    // 특정 가게의 미션 목록 조회
    public MissionResDTO.MissionListResponse getStoreMissions(MissionReqDTO.StoreMissionListRequest dto) {
        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<Mission> missionPage = missionRepository.findStoreMissions(
                dto.storeId(),
                pageRequest
        );

        return MissionConverter.toMissionListResponse(missionPage.getContent());
    }

    // 유저의 현재 진행 중/완료 미션 목록 조회
    public MissionResDTO.UserMissionListResponse getUserMissions(MissionReqDTO.UserMissionListRequest dto) {
        PageRequest pageRequest = PageRequest.of(0, 10);

        MissionStatus missionStatus = convertMissionStatus(dto.missionStatus());

        Page<UserMission> userMissionPage = userMissionRepository.findUserMissions(
                dto.userId(),
                missionStatus,
                pageRequest
        );

        return MissionConverter.toUserMissionListResponse(userMissionPage.getContent());
    }

    // 미션 도전
    @Transactional
    public MissionResDTO.UserMissionResponse challengeMission(MissionReqDTO.ChallengeMissionRequest dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Mission mission = missionRepository.findById(dto.missionId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));

        boolean alreadyChallenged = userMissionRepository.existsByUserIdAndMissionId(
                dto.userId(),
                dto.missionId()
        );

        if (alreadyChallenged) {
            throw new IllegalArgumentException("이미 도전 중이거나 완료한 미션입니다.");
        }

        UserMission userMission = UserMission.builder()
                .user(user)
                .mission(mission)
                .missionStatus(MissionStatus.IN_PROGRESS)
                .assignedAt(LocalDateTime.now())
                .completedAt(null)
                .build();

        UserMission savedUserMission = userMissionRepository.save(userMission);

        return MissionConverter.toUserMissionResponse(savedUserMission);

    }

    private MissionStatus convertMissionStatus(String missionStatus) {
        if (missionStatus.equals("진행중")) {
            return MissionStatus.IN_PROGRESS;
        }

        if (missionStatus.equals("진행완료") || missionStatus.equals("완료")) {
            return MissionStatus.COMPLETED;
        }

        return MissionStatus.valueOf(missionStatus);
    }
}
