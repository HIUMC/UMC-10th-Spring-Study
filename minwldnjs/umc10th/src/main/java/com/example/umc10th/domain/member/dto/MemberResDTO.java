package com.example.umc10th.domain.member.dto;

import lombok.*;
import java.util.List;

public class MemberResDTO {

    @Builder @Getter @NoArgsConstructor @AllArgsConstructor
    public static class MyPageDTO {
        private String name;
        private String email;
        private String phoneNumber;
        private Integer point;
    }


    @Builder @Getter @NoArgsConstructor @AllArgsConstructor
    public static class OngoingMissionPageDTO {
        private List<OngoingMissionDTO> missions;
        private Integer currentPage;
        private Integer totalPages;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    @Builder @Getter @NoArgsConstructor @AllArgsConstructor
    public static class OngoingMissionDTO {
        private Long missionId;
        private Integer point;
        private String missionContent;
    }
}