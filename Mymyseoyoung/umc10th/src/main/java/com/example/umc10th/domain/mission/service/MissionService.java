package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MissionRepository missionRepository;

    public MissionResponseDTO.MissionListDTO getAvailableMissions(Long regionId, Long memberId, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Mission> result = missionRepository.findAvailableMissionsByRegion(regionId, memberId, pageable);

        List<MissionResponseDTO.MissionDetailDTO> missionList = result.getContent()
                .stream()
                .map(m -> MissionResponseDTO.MissionDetailDTO.builder()
                        .missionId(m.getId())
                        .title(m.getTitle())
                        .description(m.getDescription())
                        .reward(m.getRewardPoints())
                        .address(m.getStore().getRegion().getName())
                        .regionId(m.getStore().getRegion().getId())
                        .regionName(m.getStore().getRegion().getName().getDescription())
                        .build())
                .toList();

        return MissionResponseDTO.MissionListDTO.builder()
                .missionList(missionList)
                .hasNext(result.hasNext())
                .page(page)
                .build();
    }
}