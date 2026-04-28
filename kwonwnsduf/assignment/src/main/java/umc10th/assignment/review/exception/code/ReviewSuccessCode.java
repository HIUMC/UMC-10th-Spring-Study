package umc10th.assignment.review.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc10th.assignment.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    CREATE_REVIEW(HttpStatus.OK, "REVIEW200_1", "성공적으로 리뷰를 작성했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
