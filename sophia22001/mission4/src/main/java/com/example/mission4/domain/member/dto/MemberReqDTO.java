package com.example.mission4.domain.member.dto;


import com.example.mission4.domain.member.enums.Gender;
import com.example.mission4.domain.mission.enums.Address;
import com.example.mission4.global.common.enums.FoodName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    // 유저 회원가입
    public record SignUp(
        @NotNull(message = "유저 이름은 필수입니다.")
        String name,
        @NotNull(message = "성은 필수입니다.")
        Gender gender,
        @NotNull(message = "생일은 필수입니다.")
        LocalDate birth,
        @NotNull(message = "주소는 필수입니다.")
        Address address,

        @NotNull(message = "상세 주소는 필수입니다.")
        String detailAddress,

        @NotNull(message = "이메일은 필수입니다.")
        String email,
        @NotNull(message = "비밀번호는 필수입니다.")
        String password,

        @NotNull(message = "선호 음식은 필수입니다.")
        @Size(min = 1, message = "선호 음식은 하나 이상이어야 합니다.")
        List<FoodName> preferFoods
    ){}

    public record Login(
        @NotNull(message = "이메일은 필수입니다.")
        String email,

        @NotNull(message = "비밀번호는 필수입니다.")
        String password
    ){}
}
