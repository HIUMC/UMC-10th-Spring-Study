package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MissionDetailDTO toMissionDetailDTO(Mission mission) {
        return MissionResDTO.MissionDetailDTO.builder()
                .missionId(mission.getId())
                .restaurantName(mission.getRestaurant().getName())
                .price(mission.getPrice())
                .reward(mission.getReward())
                .deadline(mission.getDeadline().toString())
                .build();
    }

    public static MissionResDTO.AvailableMissionListDTO toAvailableMissionListDTO(List<Mission> missions, Integer size) {
        List<MissionResDTO.MissionDetailDTO> missionDTOList = missions.stream()
                .map(MissionConverter::toMissionDetailDTO)
                .collect(Collectors.toList());

        boolean hasNext = missions.size() > size;
        Long nextCursor = null;

        if (hasNext) {
            missionDTOList.remove(missionDTOList.size() - 1);
            nextCursor = missionDTOList.get(missionDTOList.size() - 1).missionId();
        } else if (!missionDTOList.isEmpty()) {
            nextCursor = missionDTOList.get(missionDTOList.size() - 1).missionId();
        }

        return MissionResDTO.AvailableMissionListDTO.builder()
                .missions(missionDTOList)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

    public static MissionResDTO.MyMissionDetailDTO toMyMissionDetailDTO(MemberMission memberMission) {
        return MissionResDTO.MyMissionDetailDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .restaurantName(memberMission.getMission().getRestaurant().getName())
                .reward(memberMission.getMission().getReward())
                .status(memberMission.getStatus().name())
                .build();
    }

    public static MissionResDTO.MyMissionListDTO toMyMissionListDTO(List<MemberMission> myMissions, Integer size) {
        List<MissionResDTO.MyMissionDetailDTO> dtoList = myMissions.stream()
                .map(MissionConverter::toMyMissionDetailDTO)
                .collect(Collectors.toList());

        boolean hasNext = myMissions.size() > size;
        Long nextCursor = null;

        if (hasNext) {
            dtoList.remove(dtoList.size() - 1);
            nextCursor = dtoList.get(dtoList.size() - 1).memberMissionId();
        } else if (!dtoList.isEmpty()) {
            nextCursor = dtoList.get(dtoList.size() - 1).memberMissionId();
        }

        return MissionResDTO.MyMissionListDTO.builder()
                .myMissions(dtoList)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

    public static MissionResDTO.MyMissionDTO toMyMissionDTO(MemberMission memberMission) {
        return MissionResDTO.MyMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .restaurantName(memberMission.getMission().getRestaurant().getName())
                .reward(memberMission.getMission().getReward())
                .status(memberMission.getStatus().name())
                .build();
    }

    public static MissionResDTO.Pagination<MissionResDTO.MyMissionDTO> toMyMissionPaginationDTO(Page<MemberMission> page) {
        List<MissionResDTO.MyMissionDTO> data = page.getContent().stream()
                .map(MissionConverter::toMyMissionDTO)
                .collect(Collectors.toList());

        return MissionResDTO.Pagination.<MissionResDTO.MyMissionDTO>builder()
                .data(data)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }
}