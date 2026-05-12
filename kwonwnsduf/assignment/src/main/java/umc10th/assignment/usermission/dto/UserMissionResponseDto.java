package umc10th.assignment.usermission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class UserMissionResponseDto {
    @Builder
    public record GetMyMission(
            Long missionId,
            String storeName,
            Integer point,
            Integer spentPrice,
            LocalDate deadline,
            String status
    ) {
    }

    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }
}
