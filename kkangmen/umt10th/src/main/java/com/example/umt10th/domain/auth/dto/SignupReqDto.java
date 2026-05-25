package com.example.umt10th.domain.auth.dto;

import com.example.umt10th.domain.member.enums.Address;
import com.example.umt10th.domain.member.enums.FoodType;
import com.example.umt10th.domain.member.enums.Gender;
import com.example.umt10th.domain.member.enums.TermName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class SignupReqDto {

    @Builder
    public record Signup(

            List<TermName> termNameList,

            @Size(max = 5, message = "이름은 최대 5글자입니다.")
            @NotBlank(message = "이름은 필수입니다.")
            String name,

            Gender gender,
            LocalDate birth,
            Address address,
            String detailAddress,
            List<FoodType> foodList,

            @NotBlank(message = "이메일은 필수입니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            String password
    ){}

    public record Terms(
            Boolean age,
            Boolean service,
            Boolean privacy,
            Boolean location,
            Boolean marketing
    ){}
}
