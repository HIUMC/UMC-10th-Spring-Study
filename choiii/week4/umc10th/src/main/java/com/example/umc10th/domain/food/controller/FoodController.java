package com.example.umc10th.domain.food.controller;


import com.example.umc10th.domain.food.dto.request.FoodCreateRequest;
import com.example.umc10th.domain.food.dto.request.UserFoodCreateRequest;
import com.example.umc10th.domain.food.dto.response.FoodResponse;
import com.example.umc10th.domain.food.service.FoodService;
import com.example.umc10th.global.api.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/foods")
    public ApiResponse<FoodResponse> createFood(@RequestBody @Valid FoodCreateRequest request) {
        return ApiResponse.created(foodService.createFood(request));
    }

    @PostMapping("/users/{userId}/foods")
    public ApiResponse<Void> addFavoriteFood(@PathVariable Long userId, @RequestBody @Valid UserFoodCreateRequest request) {
        foodService.addFavoriteFood(userId, request);
        return ApiResponse.ok(null);
    }

    @GetMapping("/users/{userId}/foods")
    public ApiResponse<List<FoodResponse>> getFavoriteFoods(@PathVariable Long userId) {
        return ApiResponse.ok(foodService.getFavoriteFoods(userId));
    }
}
