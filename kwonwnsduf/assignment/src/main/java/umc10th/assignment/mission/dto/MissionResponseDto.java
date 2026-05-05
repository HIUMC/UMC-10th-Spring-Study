package umc10th.assignment.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResponseDto {
    public record MissionPreviewDTO(
            Long missionId,
            String storeName,
            Integer point,
            Integer spentPrice,
            LocalDate deadline
    ) {
    }

    public record MissionPreviewListDTO(
            List<MissionPreviewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }
}
