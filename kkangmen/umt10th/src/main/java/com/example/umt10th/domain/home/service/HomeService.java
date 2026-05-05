package com.example.umt10th.domain.home.service;

import com.example.umt10th.domain.home.dto.HomeResDto;
import com.example.umt10th.domain.mission.entity.Mission;
import com.example.umt10th.domain.mission.repository.LocationRepository;
import com.example.umt10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final LocationRepository locationRepository;
    private final MissionRepository missionRepository;

    public HomeResDto.HomeResponseDto getHomePage(Long locateId, Long cursor) {

        String locateName = locationRepository.findById(locateId)
                .orElseThrow()
                .getLocateName();

        HomeResDto.CurrentRegion region = HomeResDto.CurrentRegion.builder()
                .regionId(locateId)
                .locateName(locateName)
                .build();

        Pageable pageable = PageRequest.of(0, 10);
        List<Mission> missionList = missionRepository.findAllByLocateId(locateId, cursor, pageable);

        List<HomeResDto.Mission> missions = new ArrayList<>();
        for (Mission mission : missionList) {
            HomeResDto.Mission findMission = HomeResDto.Mission.builder()
                    .missionId(mission.getId())
                    .point(mission.getPoint())
                    .storeName(mission.getStore().getStoreName())
                    .deadline(mission.getDeadline())
                    .build();

            missions.add(findMission);
        }

        return HomeResDto.HomeResponseDto.builder()
                .currentRegion(region)
                .clearedMissionCount(10)
                .missionList(missions)
                .build();
    }
}
