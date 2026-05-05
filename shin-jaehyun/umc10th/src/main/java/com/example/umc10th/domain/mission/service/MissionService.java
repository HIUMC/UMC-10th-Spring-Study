package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    // 지역 미션 조회
    public List<MissionResDTO.Missions> getMissionsByRegion(Double minLat, Double maxLat, Double minLng, Double maxLng) {
        List<Mission> missions = missionRepository.findMissionsByRegion(minLat, maxLat, minLng, maxLng);

        return missions.stream()
                .map(MissionConverter::toMission)
                .toList();
    }

    private MissionResDTO.MissionCursor decode(String cursor) {
        if (cursor == null) return null;

        String decoded = new String(java.util.Base64.getDecoder().decode(cursor));
        String[] parts = decoded.split("\\|");

        return new MissionResDTO.MissionCursor(
                LocalDateTime.parse(parts[0]),
                Long.valueOf(parts[1])
        );
    }

    // 나의 미션 목록 조회
    public List<MissionResDTO.Missions> getMyMissions(Long memberId, Status status, String cursor) {
        List<MissionResDTO.Missions> missions;
        MissionResDTO.MissionCursor decodedCursor = decode(cursor);

        if (status == null) {
            missions = missionRepository.findMyMissions(memberId, decodedCursor.lastCreatedAt(), decodedCursor.lastMissionId());
        } else {
            missions = missionRepository.findMyMissionsByStatus(memberId, status, decodedCursor.lastCreatedAt(), decodedCursor.lastMissionId());
        }

        return missions;
    }

    public MissionResDTO.MissionsCount getCompletedMissionCount(Long memberId) {

        Long count = missionRepository.countCompletedMission(memberId);

        return MissionConverter.toMissionCount(count);
    }
}
