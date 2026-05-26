package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.*;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 클래스 레벨에서는 전체적인 조회 성능 최적화하기 위해 사용
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberTermRepository memberTermRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final TermRepository termRepository;
    private final FoodRepository foodRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /* 회원 가입 */
    @Transactional
    public MemberResDTO.SignupResDTO signup(MemberReqDTO.SignupReqDTO request) {
        if (memberRepository.findByEmail(request.getUserId()).isPresent()) {
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Member member = MemberConverter.toMember(request, encodedPassword);
        return MemberConverter.toSignupResDTO(memberRepository.save(member));
    }

    /* 로그인 */
    public MemberResDTO.Login login(MemberReqDTO.LoginReqDTO request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberException(MemberErrorCode.LOGIN_FAILED));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.LOGIN_FAILED);
        }

        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));
        return MemberConverter.toLogin(accessToken);
    }

    /* 회원 탈퇴 */
    @Transactional
    public void withdraw() {
    }

    /* 내 정보 조회 */
    public MemberResDTO.GetInfo getInfo(
            AuthMember member
        ) {
            // 컨버터를 이용해서 응답 DTO 생성 & 리턴
            return MemberConverter.toGetInfo(member.getMember());
    }



    /* 문의사항 작성 */
    @Transactional
    public void createQna(MemberReqDTO.QnaReqDTO request) {
    }

    /* 닉네임 변경 */
    @Transactional
    public MemberResDTO.NicknameUpdateResDTO updateNickname(MemberReqDTO.NicknameUpdateReqDTO request) {
        return MemberResDTO.NicknameUpdateResDTO.builder()
                .memberId(0L)
                .nickname(request.getNickname())
                .build();
    }

    @Transactional
    public MemberResDTO.NoticeSettingResDTO updateNoticeSetting(MemberReqDTO.NoticeSettingReqDTO request) {
        return MemberResDTO.NoticeSettingResDTO.builder()
                .isReviewPushEnabled(request.isReviewPushEnabled())
                .newEventAlarmEnabled(request.isNewEventAlarmEnabled())
                .qnaAnswerAlarmEnabled(request.isQnaAnswerAlarmEnabled())
                .build();
    }
}
