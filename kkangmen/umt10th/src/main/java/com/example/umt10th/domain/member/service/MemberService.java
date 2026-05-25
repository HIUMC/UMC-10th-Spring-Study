package com.example.umt10th.domain.member.service;

import com.example.umt10th.domain.auth.dto.SignupReqDto;
import com.example.umt10th.domain.auth.dto.SignupResDto;
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
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

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
     * 회원 정보 조회
     */
    public MemberResDTO.GetInfo getInfo() {

        // Authenticatio에서 memberId 추출
        Long memberId = 1L;
        // DB에서 해당 유저 ID로 데이터 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 컨버터를 이용해서 응답 DTO 생성 & return
        return MemberConverter.toGetInfo(member);
    }
}
