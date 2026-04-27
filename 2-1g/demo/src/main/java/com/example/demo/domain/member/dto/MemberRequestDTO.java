package com.example.demo.domain.member.dto;

import com.example.demo.domain.member.enums.Gender;
import lombok.Getter;

import java.time.LocalDate;

public class MemberRequestDTO {

    @Getter
    public static class SignUpRequest {
        private String name;
        private Gender gender;
        private LocalDate birth;
        private String address;
        private Boolean agreement;
        private String email;
        private String number;
        private String nickname;
    }

    @Getter
    public static class HomeSummaryRequest {
        private Long regionId;
    }
}
