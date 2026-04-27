package com.example.demo.domain.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Member {
    private final String name;
    private final String profileUrl;
    private final String email;
    private final String phoneNumber;
    private final Integer point;
}
