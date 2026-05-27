package com.example.umt10th.domain.auth.service;

import com.example.umt10th.domain.auth.dto.req.LoginReqDto;
import com.example.umt10th.domain.auth.dto.req.SignupReqDto;
import com.example.umt10th.domain.auth.dto.res.LoginResDto;
import com.example.umt10th.domain.auth.dto.res.SignupResDto;
import com.example.umt10th.domain.member.converter.MemberConverter;
import com.example.umt10th.domain.member.entity.Food;
import com.example.umt10th.domain.member.entity.Member;
import com.example.umt10th.domain.member.entity.Term;
import com.example.umt10th.domain.member.entity.mapping.MemberFood;
import com.example.umt10th.domain.member.entity.mapping.MemberTerm;
import com.example.umt10th.domain.member.enums.FoodType;
import com.example.umt10th.domain.member.enums.TermName;
import com.example.umt10th.domain.member.repository.*;
import com.example.umt10th.global.security.entity.AuthMember;
import com.example.umt10th.global.security.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final PasswordEncoder passwordEncoder;
    private final TermRepository termRepository;
    private final MemberTermRepository memberTermRepository;

    /***
     * Sign up
     */
    public  SignupResDto.SignupComp saveMember(SignupReqDto.Signup dto){

        // 비밀번호 암호화하여 저장
        String encodedPw = passwordEncoder.encode(dto.password());

        // 멤버 저장
        Member member = MemberConverter.createMember(dto, encodedPw);
        memberRepository.save(member);

        // 멤버-음식 저장
        for (FoodType type : dto.foodList()){
            Food food = foodRepository.findByFoodType(type)
                    .orElseThrow();

            MemberFood memberFood = MemberFood.builder()
                    .member(member)
                    .food(food)
                    .build();

            memberFoodRepository.save(memberFood);
        }

        // 멤버-동의 저장
        for (TermName name : dto.termNameList()){
            Term term = termRepository.findByTermName(name)
                    .orElseThrow();

            MemberTerm memberTerm = MemberTerm.builder()
                    .member(member)
                    .term(term)
                    .build();

            memberTermRepository.save(memberTerm);
        }

        return SignupResDto.SignupComp.builder()
                .createdAt(LocalDateTime.now())
                .build();
    }

    /***
     * login
     * 1. email 및 pw 검증
     * 2. accessToken 발급 및 반환
     *
     */
    public LoginResDto.LoginRes login(LoginReqDto.LoginReq dto) {

        // 사용자가 입력한 이메일과 pw로 임시 인증 객체 생성 (email, pw가 맞는 지 확인용)
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());

        // AuthenticationManger가 내부적으로 CustomUserDetailsService를 호출하여 email, pw 검증
        Authentication auth = authenticationManager.authenticate(authToken);

        // 검증 성공 시, 인증된 객체에서 AuthMember를 꺼냄
        AuthMember authMember = (AuthMember) auth.getPrincipal();

        // AuthMember 정보를 바탕으로 JwtUtil을 통해 AccessToken 발급
        return LoginResDto.LoginRes.builder()
                        .accessToken(jwtUtil.createAccessToken(authMember))
                        .build();
    }
}
