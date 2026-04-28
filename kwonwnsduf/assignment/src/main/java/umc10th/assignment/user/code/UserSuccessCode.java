package umc10th.assignment.user.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc10th.assignment.global.apiPayload.code.BaseSuccessCode;
@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {
    GET_MY_PAGE(HttpStatus.OK, "USER200_1", "성공적으로 마이페이지를 조회했습니다."),
    UPDATE_USER_INFO(HttpStatus.OK, "USER200_2", "성공적으로 유저 정보를 수정했습니다."),
    GET_POINT(HttpStatus.OK, "USER200_3", "성공적으로 포인트를 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
