package com.example.umc10th.domain.home.service;

import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final MissionRepository missionRepository;
    private final RegionRepository regionRepository;

    @Transactional(readOnly = true)
    public HomeResDTO.HomeResponseDTO getHome(Long regionId, Long cursor) {

        String regionName = regionRepository.findById(regionId).orElseThrow().getRegion();

        HomeResDTO.RegionDTO regionDTO = HomeResDTO.RegionDTO.builder()
                .regionId(regionId)
                .regionName(regionName)
                .build();

        int size = 10;
        Pageable pageable = PageRequest.of(0,size+1);
        List<Mission> missionList = missionRepository.findAllByRegionId(regionId, cursor, pageable);

        List<HomeResDTO.MissionPreviewDTO> missions = new ArrayList<>();
        for (Mission mission : missionList) {
            HomeResDTO.MissionPreviewDTO findMission = HomeResDTO.MissionPreviewDTO.builder()
                    .missionId(mission.getId())
                    .storeName(mission.getStore().getStoreName())
                    .deadline(mission.getDeadline())
                    .point(mission.getPoint())
                    .build();

            missions.add(findMission);
        }


        return HomeResDTO.HomeResponseDTO.builder()
                .currentRegion(regionDTO)
                .clearedMissionCount(10)
                .missionList(missions)
                .build();
    }
}
