package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public class MemberReqDTO {

    @Getter
    @Setter
    public static class JoinDTO {

        String name;
        String email;
        String password;
        Gender gender;
        LocalDateTime birthday;
        String address;
        String nickName;
        String phoneNumber;
    }

    @Getter
    @Builder
    public static class LoginDTO{
        String email;
        String password;
    }


}