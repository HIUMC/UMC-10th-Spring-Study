package com.example.umc10th.domain.user.dto.request;

import com.example.umc10th.domain.common.enums.Address;
import com.example.umc10th.domain.common.enums.Gender;
import com.example.umc10th.domain.common.enums.SocialProvider;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record UserCreateRequest(
        @NotBlank String name,
        @NotNull Gender gender,
        @NotNull LocalDate birthDate,
        @NotNull Address address,
        String profileUrl,
        @Email @NotBlank String email,
        String phoneNumber,
        @NotNull SocialProvider socialProvider,
        @NotBlank String socialId
) {}
