package com.example.umc10th.domain.user.dto.request;

import jakarta.validation.constraints.NotNull;

public record UserTermsRequest(
        @NotNull Boolean requiredAgreed,
        @NotNull Boolean locationAgreed,
        @NotNull Boolean marketingAgreed
) {}
