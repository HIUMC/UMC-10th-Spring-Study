package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseSuccessCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404_1", "해당 유저를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "USER409_1", "이미 존재하는 유저입니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
