package com.example.umc10th.domain.mission.dto;
import lombok.*;
import java.util.List;

public class MissionResDTO {
    @Builder @Getter @NoArgsConstructor @AllArgsConstructor
    public static class MissionListDTO {
        private List<MissionDetailDTO> missionList;
    }

    @Builder @Getter @NoArgsConstructor @AllArgsConstructor
    public static class MissionDetailDTO {
        private Long missionId;
        private String title;
        private Integer reward;
        private String status;
    }
}
