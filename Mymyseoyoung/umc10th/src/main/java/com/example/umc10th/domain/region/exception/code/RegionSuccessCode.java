package com.example.umc10th.domain.region.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RegionSuccessCode implements BaseSuccessCode {
    EGION_UPDATED(HttpStatus.OK, "REGION200", "활동 지역이 성공적으로 변경되었습니다."),
    PROGRESS_FOUND(HttpStatus.OK, "REGION200_1", "현재 지역의 미션 진행률을 조회했습니다.");

    private final HttpStatus status; // 필드명 status 확인!
    private final String code;
    private final String message;
}
