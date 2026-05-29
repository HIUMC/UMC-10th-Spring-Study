package com.example.mission4.domain.mypage.service;

import com.example.mission4.domain.member.entity.Member;
import com.example.mission4.domain.member.exception.MemberException;
import com.example.mission4.domain.member.exception.code.MemberErrorCode;
import com.example.mission4.domain.member.repository.MemberRepository;
import com.example.mission4.domain.mypage.converter.MypageConverter;
import com.example.mission4.domain.mypage.dto.MypageResDTO;
import com.example.mission4.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MypageService {

    private final MemberRepository memberRepository;

    public MypageResDTO.GetMypage getMypage(AuthMember member) {
        return MypageConverter.toGetMypage(member.getMember());
    }
}
