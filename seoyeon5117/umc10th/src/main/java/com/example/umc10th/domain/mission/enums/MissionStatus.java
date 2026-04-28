package com.example.umc10th.domain.mission.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MissionStatus {
    IN_PROGRESS("진행중"),
    COMPLETED("진행완료");

    private final String description;
}