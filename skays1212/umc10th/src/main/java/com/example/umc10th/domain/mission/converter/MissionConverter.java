package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionItemDTO toMissionItemDTO(Mission mission) {
        return MissionResDTO.MissionItemDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName()) // 각 미션 하나당 getStore() 한 번씩 호출(n+1) -> Fetch Join 필요!
                .region(mission.getStore().getLocation().getName().name()) // 미션 하나당 getStore(), getLocation() 한 번씩 호출(n+1) -> Fetch Join 필요!
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .build();
    }

    public static MissionResDTO.RegionMissionResDTO toRegionMissionResDTO(Page<Mission> page) {
        List<MissionResDTO.MissionItemDTO> items = page.getContent().stream()
                .map(MissionConverter::toMissionItemDTO)
                .toList();
        return MissionResDTO.RegionMissionResDTO.builder()
                .missions(items)
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isLast(page.isLast())
                .build();
    }

    public static MissionResDTO.MyMissionItemDTO toMyMissionItemDTO(MemberMission mm) {
        return MissionResDTO.MyMissionItemDTO.builder()
                .missionId(mm.getMission().getId())
                .storeName(mm.getMission().getStore().getName())
                .conditional(mm.getMission().getConditional())
                .point(mm.getMission().getPoint())
                .isComplete(mm.getIsComplete())
                .build();
    }

    public static MissionResDTO.MyMissionResDTO toMyMissionResDTO(Page<MemberMission> page) {
        List<MissionResDTO.MyMissionItemDTO> items = page.getContent().stream()
                .map(MissionConverter::toMyMissionItemDTO)
                .toList();
        return MissionResDTO.MyMissionResDTO.builder()
                .missions(items)
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isLast(page.isLast())
                .build();
    }

    public static MissionResDTO.MissionChallengeResDTO toMissionChallengeResDTO(MemberMission mm) {
        return MissionResDTO.MissionChallengeResDTO.builder()
                .memberMissionId(mm.getId())
                .missionId(mm.getMission().getId())
                .isComplete(mm.getIsComplete())
                .build();
    }
}
