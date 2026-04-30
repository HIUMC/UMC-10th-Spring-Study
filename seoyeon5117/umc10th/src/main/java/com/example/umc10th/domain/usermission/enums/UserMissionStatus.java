package com.example.umc10th.domain.usermission.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserMissionStatus {
    IN_PROGRESS("진행중"),
    COMPLETED("진행완료");

    private final String description;
}