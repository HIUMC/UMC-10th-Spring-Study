package com.example.umt10th.domain.member.dto;

import com.example.umt10th.domain.member.enums.Address;
import com.example.umt10th.domain.member.enums.Gender;
import com.example.umt10th.domain.member.enums.SocialType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.time.LocalDate;

public class MemberReqDTO {

    // 회원가입
    public record saveMember(

            @NotBlank(message = "이름을 입력해 주세요.")
            String name,

            @NotNull(message = "성별을 선택해주세요.")
            Gender gender,

            @NotNull(message = "생년월일을 입력해주세요.")
            LocalDate birth,

            @NotNull(message = "주소를 입력해주세요.")
            Address address,

            @NotBlank(message = "상세 주소를 입력해주세요.")
            String detailAddress,

            @NotBlank(message = "소셜 식별자가 누락되었습니다.")
            String socialUuid,

            @NotNull(message = "소셜 로그인 타입이 누락되었습니다.")
            SocialType socialType,

            Integer point,

            @Email(message = "올바른 이메일 형식이 아닙니다.")
            String email,

            @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "올바른 전화번호 형식이 아닙니다. (예: 010-1111-1111")
            String phoneNumber
    ){
        public saveMember {
            if (point == null){
                point = 0;
            }
        }
    }
}
