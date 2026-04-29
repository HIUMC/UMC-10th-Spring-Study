package com.example.umc10th.domain.food.dto.request;

import jakarta.validation.constraints.NotBlank;

public record FoodCreateRequest(@NotBlank String foodName) {}
