package com.example.mission4.domain.review.exception.code;

import com.example.mission4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode{

    // 리뷰 등록 성공
    REVIEW_REGISTERED(HttpStatus.OK, "REVIEW201_1", "성공적으로 리뷰가 등록되었습니다."),

    // 리뷰 조회 성공
    REVIEW_FOUND(HttpStatus.OK, "REVIEW200_1", "성공적으로 리뷰가 조회되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
