package umc10th.assignment.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc10th.assignment.global.apiPayload.ApiResponse;
import umc10th.assignment.global.apiPayload.code.BaseSuccessCode;
import umc10th.assignment.user.code.UserSuccessCode;
import umc10th.assignment.user.dto.UserJoinRequest;
import umc10th.assignment.user.dto.UserRequestDto;
import umc10th.assignment.user.dto.UserResponseDto;
import umc10th.assignment.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ApiResponse<UserResponseDto.Login> login(
            @RequestBody UserRequestDto.Login request
    ) {
        BaseSuccessCode code = UserSuccessCode.LOGIN;
        return ApiResponse.onSuccess(code, userService.login(request));
    }
    @PostMapping("/signup")
    public ApiResponse<Long> signup(
            @RequestBody UserJoinRequest request
    ) {
        BaseSuccessCode code = UserSuccessCode.SIGNUP;
        Long memberId = userService.join(request);
        return ApiResponse.onSuccess(code, memberId);
    }
}
