package com.example.umc10th.domain.review.enums;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATE(HttpStatus.CREATED, "REVIEW201", "리뷰가 성공적으로 등록되었습니다."),
    REVIEW_GET(HttpStatus.OK, "REVIEW200_1", "리뷰가 성공적으로 조회되었습니다."),
    REVIEW_UPDATE(HttpStatus.OK, "REVIEW200_2", "리뷰가 성공적으로 수정되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}