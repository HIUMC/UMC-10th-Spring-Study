package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.request.MissionCreateRequest;
import com.example.umc10th.domain.mission.dto.request.UserMissionCompleteRequest;
import com.example.umc10th.domain.mission.dto.request.UserMissionCreateRequest;
import com.example.umc10th.domain.mission.dto.response.MissionResponse;
import com.example.umc10th.domain.mission.dto.response.UserMissionResponse;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.UserMission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.service.StoreService;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.exception.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final StoreService storeService;
    private final UserService userService;

    @Transactional
    public MissionResponse createMission(MissionCreateRequest request) {
        Store store = storeService.findStore(request.storeId());
        Mission mission = missionRepository.save(MissionConverter.toEntity(request, store));
        return MissionConverter.toMissionResponse(mission);
    }

    @Transactional
    public UserMissionResponse challengeMission(Long userId, UserMissionCreateRequest request) {
        User user = userService.findUser(userId);
        Mission mission = findMission(request.missionId());
        UserMission userMission = userMissionRepository.save(MissionConverter.toUserMissionEntity(user, mission));
        return MissionConverter.toUserMissionResponse(userMission);
    }

    @Transactional
    public UserMissionResponse completeMission(Long userMissionId, UserMissionCompleteRequest request) {
        UserMission userMission = findUserMission(userMissionId);
        userMission.complete(request.verificationCode(), LocalDateTime.now());
        userMission.getUser().addPoint(userMission.getMission().getRewardPoint() == null ? 0 : userMission.getMission().getRewardPoint());
        return MissionConverter.toUserMissionResponse(userMission);
    }

    public List<UserMissionResponse> getUserMissions(Long userId) {
        return userMissionRepository.findAllByUserId(userId).stream()
                .map(MissionConverter::toUserMissionResponse)
                .toList();
    }

    public Mission findMission(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 미션입니다. missionId=" + missionId));
    }

    public UserMission findUserMission(Long userMissionId) {
        return userMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 유저 미션입니다. userMissionId=" + userMissionId));
    }
}
