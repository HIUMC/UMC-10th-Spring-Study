package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.data.domain.Slice;

public class MissionConverter {

    public static MissionResDTO.Info toInfo(Mission mission) {
        return MissionResDTO.Info.builder()
                .id(mission.getId())
                .restaurantName(mission.getRestaurant().getName())
                .category(mission.getRestaurant().getCategory().getName())
                .daysLeft(ChronoUnit.DAYS.between(LocalDate.now(), mission.getDeadline()))
                .content(mission.getContent())
                .point(mission.getPoint())
                .build();

    }

    public static MissionResDTO.InfoList toInfoList(List<Mission> missions) {
        return MissionResDTO.InfoList.builder()
                .infos(missions.stream()
                        .map(MissionConverter::toInfo)
                        .toList())
                .build();
    }

    public static MissionResDTO.InfoSlice toInfoSlice(Slice<Mission> slice) {
        return MissionResDTO.InfoSlice.builder()
                .infoList(toInfoList(slice.getContent()))
                .nextCursor(slice.isEmpty() ? null :
                        slice.getContent().getLast().getId())
                .hasNext(slice.hasNext())
                .build();
    }
}
