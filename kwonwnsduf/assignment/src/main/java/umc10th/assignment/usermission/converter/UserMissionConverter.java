package umc10th.assignment.usermission.converter;

import org.springframework.data.domain.Page;
import umc10th.assignment.usermission.dto.UserMissionResponseDto;
import umc10th.assignment.usermission.entity.UserMission;

import java.util.List;

public class UserMissionConverter {
    public static UserMissionResponseDto.GetMyMission toGetMyMission(UserMission userMission) {
        return UserMissionResponseDto.GetMyMission.builder()
                .missionId(userMission.getMission().getMissionId())
                .storeName(userMission.getMission().getStore().getName())
                .point(userMission.getMission().getPoint())
                .spentPrice(userMission.getMission().getSpentPrice())
                .deadline(userMission.getMission().getDeadline())
                .status(userMission.getStatus().name())
                .build();
    }

    public static <T> UserMissionResponseDto.Pagination<T> toPagination(
            Page<T> page
    ) {
        List<T> data = page.getContent();

        return UserMissionResponseDto.Pagination.<T>builder()
                .data(data)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
