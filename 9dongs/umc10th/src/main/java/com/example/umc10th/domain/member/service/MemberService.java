package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member signUp(MemberReqDTO.SignUpDTO request) {
        if (memberRepository.findByEmail(request.email()).isPresent()) {
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        // 비밀번호 암호화 (BCrypt)
        String encodedPassword = passwordEncoder.encode(request.password());

        // 더미값을 포함한 Member 엔티티 생성
        Member newMember = MemberConverter.toMember(request, encodedPassword);

        // Member 저장
        return memberRepository.save(newMember);
    }

    public MemberResDTO.MyPageDTO getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
        Integer totalPoint = pointRepository.sumPointChangeByMemberId(memberId).orElse(0);

        return MemberConverter.toMyPageDTO(member, totalPoint);
    }
}