package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
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
public class MissionService {

    private final MissionRepository missionRepository;

    public MissionResDTO.MissionListDTO getHomeMissions(String region, int page) {
        Page<Mission> missions = missionRepository
                .findByLocationName(region, PageRequest.of(page - 1, 10));

        List<MissionResDTO.MissionDetailDTO> missionList = missions.stream()
                .map(m -> MissionResDTO.MissionDetailDTO.builder()
                        .missionId(m.getId())
                        .title(m.getMissionContent())
                        .reward(m.getPoint())
                        .status("진행 가능")
                        .build())
                .collect(Collectors.toList());

        return MissionResDTO.MissionListDTO.builder()
                .missionList(missionList)
                .build();
    }
}