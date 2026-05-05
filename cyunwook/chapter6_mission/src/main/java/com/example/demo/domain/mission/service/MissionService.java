package com.example.demo.domain.mission.service;

import com.example.demo.domain.mission.dto.MissionResDTO;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.mapping.MemberMission;
import com.example.demo.domain.mission.repository.MemberMissionRepository;
import com.example.demo.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    // 내 미션 조회 (진행중/완료)
    public MissionResDTO.MissionPageDTO getMyMissions(
            Long memberId, Boolean isComplete, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<MemberMission> result =
                memberMissionRepository.findByMemberIdAndStatus(memberId, isComplete, pageable);

        List<MissionResDTO> dtos = result.getContent().stream()
                .map(mm -> MissionResDTO.builder()
                        .missionId(mm.getMission().getId())
                        .storeName(mm.getMission().getStore().getName())
                        .conditional(mm.getMission().getConditional())
                        .point(mm.getMission().getPoint())
                        .deadline(mm.getMission().getDeadline())
                        .isComplete(mm.getIsComplete())
                        .build())
                .toList();

        return MissionResDTO.MissionPageDTO.builder()
                .missions(dtos)
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .currentPage(page)
                .build();
    }

    // 홈 화면 - 지역별 미션 조회
    public MissionResDTO.MissionPageDTO getMissionsByLocation(
            Long locationId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Mission> result = missionRepository.findByLocationId(locationId, pageable);

        List<MissionResDTO> dtos = result.getContent().stream()
                .map(m -> MissionResDTO.builder()
                        .missionId(m.getId())
                        .storeName(m.getStore().getName())
                        .conditional(m.getConditional())
                        .point(m.getPoint())
                        .deadline(m.getDeadline())
                        .build())
                .toList();

        return MissionResDTO.MissionPageDTO.builder()
                .missions(dtos)
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .currentPage(page)
                .build();
    }
}
