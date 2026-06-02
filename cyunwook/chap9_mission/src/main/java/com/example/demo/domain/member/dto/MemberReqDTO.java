package com.example.demo.domain.member.dto;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.enums.Gender;
import com.example.demo.domain.member.enums.SocialType;
import com.example.demo.domain.member.enums.Address;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

public class MemberReqDTO {
    public record GetInfo(
            Long id
    ){}
    @Getter
    public static class CreateMember {
        @NotBlank(message = "이름은 필수입니다.")
        @Size(max = 5, message = "이름은 5자 이하여야 합니다.")
        private String name;

        @NotNull(message = "성별은 필수입니다.")
        private Gender gender;

        @NotNull(message = "생일은 필수입니다.")
        private LocalDate birth;

        @NotNull(message = "주소는 필수입니다.")
        private Address address;

        @NotBlank(message = "상세주소는 필수입니다.")
        private String detailedAddress;

        @NotBlank(message = "소셜 ID는 필수입니다.")
        private String socialUid;

        @NotNull(message = "소셜 타입은 필수입니다.")
        private SocialType socialType;

        @Email(message = "이메일 형식이 아닙니다.")
        private String email;

        @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "전화번호 형식이 맞지 않습니다.")
        private String phoneNumber;
    }
}
