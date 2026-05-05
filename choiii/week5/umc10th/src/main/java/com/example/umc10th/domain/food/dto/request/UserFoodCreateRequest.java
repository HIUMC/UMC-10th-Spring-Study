package com.example.umc10th.domain.food.dto.request;

import jakarta.validation.constraints.NotNull;

public record UserFoodCreateRequest(@NotNull Long foodId) {}
