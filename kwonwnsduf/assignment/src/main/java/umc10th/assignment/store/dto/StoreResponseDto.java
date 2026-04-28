package umc10th.assignment.store.dto;

import lombok.Builder;

import java.util.List;

public class StoreResponseDto {
    @Builder
    public record HomeMission(
            Long missionId,
            String title,
            String storeName,
            Integer reward
    ) {
    }

    @Builder
    public record Home(
            String userName,
            List<HomeMission> missions
    ) {
    }
}
