package com.example.umt10th.domain.member.service;

import com.example.umt10th.domain.auth.dto.req.LoginReqDto;
import com.example.umt10th.domain.auth.dto.req.SignupReqDto;
import com.example.umt10th.domain.auth.dto.res.LoginResDto;
import com.example.umt10th.domain.auth.dto.res.SignupResDto;
import com.example.umt10th.domain.member.converter.MemberConverter;
import com.example.umt10th.domain.member.dto.MemberResDTO;
import com.example.umt10th.domain.member.entity.Food;
import com.example.umt10th.domain.member.entity.Member;
import com.example.umt10th.domain.member.entity.Term;
import com.example.umt10th.domain.member.entity.mapping.MemberFood;
import com.example.umt10th.domain.member.entity.mapping.MemberTerm;
import com.example.umt10th.domain.member.enums.FoodType;
import com.example.umt10th.domain.member.enums.TermName;
import com.example.umt10th.domain.member.exception.MemberException;
import com.example.umt10th.domain.member.exception.code.MemberErrorCode;
import com.example.umt10th.domain.member.repository.*;
import com.example.umt10th.global.security.entity.AuthMember;
import com.example.umt10th.global.security.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    /***
     * 회원 정보 조회
     */
    public MemberResDTO.GetInfo getInfo(AuthMember member) {
        return MemberConverter.toGetInfo(member.getMember());
    }
}
