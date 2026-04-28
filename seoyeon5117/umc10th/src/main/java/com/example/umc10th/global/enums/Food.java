package com.example.umc10th.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Food {
    KOREAN("한식"),
    WESTERN("양식"),
    CHINESE("중식"),
    JAPANESE("일식"),
    CHICKEN("치킨"),
    BUNSIK("분식"),
    GRILL("고기/구이"),
    LUNCHBOX("도시락"),
    NIGHT_FOOD("야식"),
    FAST_FOOD("패스트푸드"),
    DESSERT("디저트"),
    ASIAN("아시안푸드");

    private final String description;
}
