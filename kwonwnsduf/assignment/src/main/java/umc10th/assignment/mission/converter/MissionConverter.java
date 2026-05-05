package umc10th.assignment.mission.converter;

import org.springframework.data.domain.Page;
import umc10th.assignment.mission.dto.MissionResponseDto;
import umc10th.assignment.mission.entity.Mission;
import umc10th.assignment.usermission.entity.UserMission;

import java.util.List;

public class MissionConverter {

    public static MissionResponseDto.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return new MissionResponseDto.MissionPreviewDTO(
                mission.getMissionId(),
                mission.getStore().getName(),
                mission.getPoint(),
                mission.getSpentPrice(),
                mission.getDeadline()
        );
    }

    public static MissionResponseDto.MissionPreviewDTO toMissionPreviewDTO(UserMission userMission) {
        Mission mission = userMission.getMission();

        return new MissionResponseDto.MissionPreviewDTO(
                mission.getMissionId(),
                mission.getStore().getName(),
                mission.getPoint(),
                mission.getSpentPrice(),
                mission.getDeadline()
        );
    }

    public static MissionResponseDto.MissionPreviewListDTO toMissionPreviewListDTO(
            Page<MissionResponseDto.MissionPreviewDTO> page
    ) {
        List<MissionResponseDto.MissionPreviewDTO> missionList = page.getContent();

        return new MissionResponseDto.MissionPreviewListDTO(
                missionList,
                missionList.size(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.isLast()
        );
    }
}