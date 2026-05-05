package com.example.umc10th.domain.store.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    // 가게 관련 성공
    STORE_FOUND(HttpStatus.OK, "STORE2001", "가게 정보 조회에 성공하였습니다."),
    STORE_REGISTERED(HttpStatus.OK, "STORE2002", "가게가 성공적으로 등록되었습니다."),
    STORE_REVIEW_CREATED(HttpStatus.OK, "STORE2003", "가게 리뷰가 성공적으로 등록되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}