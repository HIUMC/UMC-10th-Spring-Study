package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.request.UserCreateRequest;
import com.example.umc10th.domain.user.dto.request.UserTermsRequest;
import com.example.umc10th.domain.user.dto.response.UserResponse;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.api.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request) {
        return ApiResponse.created(userService.createUser(request));
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable Long userId) {
        return ApiResponse.ok(userService.getUser(userId));
    }

    @PostMapping("/{userId}/terms")
    public ApiResponse<Void> agreeTerms(@PathVariable Long userId, @RequestBody @Valid UserTermsRequest request) {
        userService.agreeTerms(userId, request);
        return ApiResponse.ok(null);
    }
}
