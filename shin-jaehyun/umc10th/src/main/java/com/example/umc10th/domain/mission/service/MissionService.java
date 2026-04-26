package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    // 지역 미션 조회
    public List<MissionResDTO.Missions> getMissionsByRegion(Double minLat, Double maxLat, Double minLng, Double maxLng) {
        return null;
    }

    // 나의 미션 목록 조회
    public List<MissionResDTO.Missions> getMyMissions(Long id, Status status) {
        return null;
    }

    public MissionResDTO.MissionsCount getCompletedMissionCount(Long userId) {
        return null;
    }
}
