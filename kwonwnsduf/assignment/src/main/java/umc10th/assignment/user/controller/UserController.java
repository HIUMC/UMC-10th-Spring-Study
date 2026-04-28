package umc10th.assignment.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc10th.assignment.global.apiPayload.ApiResponse;
import umc10th.assignment.global.apiPayload.code.BaseSuccessCode;
import umc10th.assignment.user.code.UserSuccessCode;
import umc10th.assignment.user.dto.UserRequestDto;
import umc10th.assignment.user.dto.UserResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    // 마이페이지 조회
    @GetMapping("/members/me")
    public ApiResponse<UserResponseDto.GetInfo> getInfo(
            @RequestBody UserRequestDto.GetInfo dto
    ) {
        BaseSuccessCode code = UserSuccessCode.GET_MY_PAGE;

        UserResponseDto.GetInfo response = UserResponseDto.GetInfo.builder()
                .name("nickname012")
                .profileUrl("https://example.com/profile.png")
                .email("user@example.com")
                .phoneNumber(null)
                .point(2500)
                .build();

        return ApiResponse.onSuccess(code, response);
    }

    // 내 정보 수정
    @PatchMapping("/members/me")
    public ApiResponse<UserResponseDto.UpdateInfo> updateInfo(
            @RequestBody UserRequestDto.UpdateInfo dto
    ) {
        BaseSuccessCode code = UserSuccessCode.UPDATE_USER_INFO;

        UserResponseDto.UpdateInfo response = UserResponseDto.UpdateInfo.builder()
                .name(dto.name())
                .profileUrl(dto.profileUrl())
                .phoneNumber(dto.phoneNumber())
                .build();

        return ApiResponse.onSuccess(code, response);
    }

    // 내 포인트 조회
    @GetMapping("/members/me/points")
    public ApiResponse<UserResponseDto.GetPoint> getPoint() {
        BaseSuccessCode code = UserSuccessCode.GET_POINT;

        UserResponseDto.GetPoint response = UserResponseDto.GetPoint.builder()
                .point(2500)
                .build();

        return ApiResponse.onSuccess(code, response);
    }
}
