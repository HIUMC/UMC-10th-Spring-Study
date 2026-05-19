package com.example.umc10th.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FoodCategory {

    KOREAN("한식"),
    JAPANESE("일식"),
    CHINESE("중식"),
    WESTERN("양식"),
    CHICKEN("치킨"),
    SCHOOL_FOOD("분식"),
    MEAT_GRLL("고기/구이"),
    LUNCH_BOX("도시락"),
    LATE_NIGHT_SNACK("야식(족발,보쌈)"),
    FAST_FOOD("패스트푸드"),
    DESSERT("디저트"),
    ASIAN("아시안푸드");

    private final String description;
}
