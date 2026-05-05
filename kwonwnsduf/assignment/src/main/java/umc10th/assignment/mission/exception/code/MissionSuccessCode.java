package umc10th.assignment.mission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc10th.assignment.global.apiPayload.code.BaseSuccessCode;
@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    GET_MISSIONS(HttpStatus.OK, "MISSION200_1", "성공적으로 미션 목록을 조회했습니다."),
    GET_MISSION_DETAIL(HttpStatus.OK, "MISSION200_2", "성공적으로 미션 상세 정보를 조회했습니다."),
    COMPLETE_MISSION(HttpStatus.OK, "MISSION200_3", "성공적으로 미션을 완료했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
