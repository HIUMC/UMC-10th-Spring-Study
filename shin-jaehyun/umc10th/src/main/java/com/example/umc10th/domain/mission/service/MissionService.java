package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    // 지역 미션 조회
    public List<MissionResDTO.getMissions> getMissionsByRegion(Double minLat, Double maxLat, Double minLng, Double maxLng) {
        List<Mission> missions = missionRepository.findMissionsByRegion(minLat, maxLat, minLng, maxLng);

        return missions.stream()
                .map(MissionConverter::toMission)
                .toList();
    }


    // 나의 미션 목록 조회
    public MissionResDTO.Pagination<MissionResDTO.getMissions> getMyMissions(Long memberId, Status status, Integer pageSize, Integer pageNumber, String sort) {

        Sort sortInfo;

        if (sort != null) {
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("created_at").descending();
        }

        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<MissionResDTO.getMissions> missionList = missionRepository.findMyMissionsByStatus(memberId, status, pageRequest);

        return MissionConverter.toPagination(
                missionList.toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }

    public MissionResDTO.MissionsCount getCompletedMissionCount(Long memberId) {

        Long count = missionRepository.countCompletedMission(memberId);

        return MissionConverter.toMissionCount(count);
    }
}
