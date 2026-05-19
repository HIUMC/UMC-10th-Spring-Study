package com.example.demo.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class MissionResDTO {

    //가게 내 미션 조회
    public record GetMission(
            Long missionId,
            Integer point,
            String conditional

    ){}

    //페이지네이션 틀

    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}

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
