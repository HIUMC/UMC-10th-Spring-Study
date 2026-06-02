package com.example.demo.domain.auth.dto;

import com.example.demo.domain.member.enums.Address;
import com.example.demo.domain.member.enums.FoodCategory;
import com.example.demo.domain.member.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class AuthReqDTO {

    @Getter
    public static class SignUp {

        @NotBlank(message = "이름은 필수입니다.")
        private String name;

        @NotNull(message = "성별은 필수입니다.")
        private Gender gender;

        @NotNull(message = "생일은 필수입니다.")
        private LocalDate birth;

        @NotNull(message = "주소는 필수입니다.")
        private Address address;

        @NotBlank(message = "상세주소는 필수입니다.")
        private String detailAddress;

        private List<FoodCategory> foodList;

        @Email(message = "이메일 형식이 아닙니다.")
        @NotBlank(message = "이메일은 필수입니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;

        private Agree agree;

        @Getter
        public static class Agree {
            private boolean age;
            private boolean service;
            private boolean privacy;
            private boolean location;
            private boolean marketing;
        }
    }
    @Getter
    public static class Login {

        @Email(message = "이메일 형식이 아닙니다.")
        @NotBlank(message = "이메일은 필수입니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;
        }
    }
