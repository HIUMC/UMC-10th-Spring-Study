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

    // 오프셋 기반 페이지네이션 응답 DTO
    @Builder @Getter @NoArgsConstructor @AllArgsConstructor
    public static class MissionPageDTO {
        private List<MissionDetailDTO> missionList;
        private Integer currentPage;
        private Integer totalPage;
        private Long totalCount;
        private Boolean isFirst;
        private Boolean isLast;
    }
}