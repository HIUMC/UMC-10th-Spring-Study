package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {



    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //마이페이지
    public MemberResDTO.GetInfo getInfo(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));


        return MemberConverter.toGetInfo(member);
    }

    @Transactional
    public MemberResDTO.JoinDTO signUp(MemberReqDTO.@Valid JoinDTO dto) {

        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        Member member = Member.builder()
                .email(dto.getEmail())
                .password(encodedPassword)
                .name(dto.getName())
                .birth(dto.getBirthday())
                .gender(dto.getGender())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        Member savedMember = memberRepository.save(member);

        return MemberConverter.joinDTO(savedMember);
    }
}
