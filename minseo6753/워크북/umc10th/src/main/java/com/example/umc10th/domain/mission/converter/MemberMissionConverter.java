package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.data.domain.Slice;

public class MemberMissionConverter {

    public static MissionResDTO.Info toInfo(MemberMission memberMission) {
        return MissionResDTO.Info.builder()
                .id(memberMission.getId())
                .restaurantName(memberMission.getMission().getRestaurant().getName())
                .category(memberMission.getMission().getRestaurant().getCategory().getName())
                .daysLeft(ChronoUnit.DAYS.between(LocalDate.now(), memberMission.getMission().getDeadline()))
                .content(memberMission.getMission().getContent())
                .point(memberMission.getMission().getPoint())
                .build();

    }

    public static MissionResDTO.InfoList toInfoList(List<MemberMission> memberMissions) {
        return MissionResDTO.InfoList.builder()
                .infos(memberMissions.stream()
                        .map(MemberMissionConverter::toInfo)
                        .toList())
                .build();
    }

    public static MissionResDTO.InfoSlice toInfoSlice(Slice<MemberMission> slice) {
        return MissionResDTO.InfoSlice.builder()
                .infoList(toInfoList(slice.getContent()))
                .nextCursor(slice.isEmpty() ? null :
                        slice.getContent().getLast().getId())
                .hasNext(slice.hasNext())
                .build();
    }

    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize,
            Integer totalPages,
            Long totalElements
    ) {
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalPages(totalPages)
                .totalElements(totalElements)
                .build();
    }
}
