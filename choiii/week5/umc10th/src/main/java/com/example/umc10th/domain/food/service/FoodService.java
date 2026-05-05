package com.example.umc10th.domain.food.service;



import java.util.List;

import com.example.umc10th.domain.food.dto.request.FoodCreateRequest;
import com.example.umc10th.domain.food.dto.request.UserFoodCreateRequest;
import com.example.umc10th.domain.food.dto.response.FoodResponse;
import com.example.umc10th.domain.food.entity.Food;
import com.example.umc10th.domain.food.entity.UserFood;
import com.example.umc10th.domain.food.repository.FoodRepository;
import com.example.umc10th.domain.food.repository.UserFoodRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodService {

    private final FoodRepository foodRepository;
    private final UserFoodRepository userFoodRepository;
    private final UserService userService;

    @Transactional
    public FoodResponse createFood(FoodCreateRequest request) {
        Food food = foodRepository.save(Food.builder().foodName(request.foodName()).build());
        return new FoodResponse(food.getId(), food.getFoodName());
    }

    @Transactional
    public void addFavoriteFood(Long userId, UserFoodCreateRequest request) {
        User user = userService.findUser(userId);
        Food food = findFood(request.foodId());
        userFoodRepository.save(UserFood.builder().user(user).food(food).build());
    }

    public List<FoodResponse> getFavoriteFoods(Long userId) {
        return userFoodRepository.findAllByUserId(userId).stream()
                .map(UserFood::getFood)
                .map(food -> new FoodResponse(food.getId(), food.getFoodName()))
                .toList();
    }

    public Food findFood(Long foodId) {
        return foodRepository.findById(foodId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 음식입니다. foodId=" + foodId));
    }
}
