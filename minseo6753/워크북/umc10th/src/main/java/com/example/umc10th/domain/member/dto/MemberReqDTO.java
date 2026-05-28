package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record SignUp(
            @Email @NotBlank
            String email,
            @NotBlank
            String password,
            @NotBlank
            String name,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            Long eupMyeonDongId,
            List<Long> categoryIds,
            List<@Valid AgreementReq> agreements
    ) {
    }

    public record AgreementReq(
            @NotNull
            Long policyId,
            @NotNull
            Boolean agreed
    ) {
    }

    public record Login(
            @Email
            String email,
            @NotBlank
            String password
    ) {
    }

    public record Location(
            String eupMyeonDong
    ) {
    }
}
