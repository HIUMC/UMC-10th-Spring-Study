package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.LoginRequest;
import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.dto.TokenResponse;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.FoodPreference;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.FoodPreferenceRepository;
import com.example.umc10th.domain.member.repository.FoodRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final FoodPreferenceRepository foodPreferenceRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;


    //회원가입
    public MemberResponseDTO.JoinResult join(MemberRequestDTO.Join request) {

        //이메일 중복 검사
        if(memberRepository.existsByEmail(request.email()))
        {
            throw new ProjectException(MemberErrorCode.EMAIL_ALREADY_EXISTS);
        }
        String encodedPassword = passwordEncoder.encode(request.password());

        //DTO 엔티티로 변환
        Member newMember = MemberConverter.toMember(request,encodedPassword);

        Member savedMember = memberRepository.save(newMember);

        // 선호 음식 저장
        if (request.foodCategories() != null && !request.foodCategories().isEmpty()) {
            List<Food> foods = foodRepository.findByFoodCategoryIn(request.foodCategories());
            List<FoodPreference> preferences = MemberConverter.toFoodPreferenceList(newMember, foods);
            foodPreferenceRepository.saveAll(preferences);
        }

        String token= jwtUtil.createAccessToken(AuthMember.from(savedMember));

        return MemberConverter.toJoinResult(savedMember,token);
    }

    public TokenResponse login(LoginRequest request)
    {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(()->new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));

        if(!passwordEncoder.matches(request.password(), member.getPassword()))
        {
            throw new ProjectException(MemberErrorCode.INVALID_PASSWORD);
        }

        String accessToken = jwtUtil.createAccessToken(AuthMember.from(member));
        return new TokenResponse(accessToken);
    }
}
