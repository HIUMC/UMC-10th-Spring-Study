package com.example.demo.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class MissionResDTO {

    private Long missionId;
    private String storeName;
    private String conditional;
    private Integer point;
    private LocalDate deadline;
    private Boolean isComplete;

    // 페이징 감싸는 DTO
    @Getter
    @Builder
    public static class MissionPageDTO {
        private List<MissionResDTO> missions;
        private int totalPage;
        private long totalElements;
        private int currentPage;
    }
}
