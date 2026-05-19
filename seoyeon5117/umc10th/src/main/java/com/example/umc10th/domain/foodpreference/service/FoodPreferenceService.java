package com.example.umc10th.domain.foodpreference.service;

import com.example.umc10th.domain.foodpreference.entity.FoodPreference;
import com.example.umc10th.domain.foodpreference.repository.FoodPreferenceRepository;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.global.enums.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodPreferenceService {

    private final FoodPreferenceRepository foodPreferenceRepository;

    public void saveFoodPreferences(Member member, List<Food> foods) {
        if (foods == null || foods.isEmpty()) return;

        List<FoodPreference> preferences = foods.stream()
                .map(food -> FoodPreference.builder().member(member).food(food).build())
                .toList();
        foodPreferenceRepository.saveAll(preferences);
    }
}
