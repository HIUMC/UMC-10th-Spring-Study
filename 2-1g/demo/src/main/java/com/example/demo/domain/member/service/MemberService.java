package com.example.demo.domain.member.service;

import com.example.demo.domain.member.converter.MemberConverter;
import com.example.demo.domain.member.dto.MemberRequestDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.enums.SocialType;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.store.entity.Region;
import com.example.demo.domain.store.repository.RegionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;
    private final PasswordEncoder passwordEncoder;

    public Member getMemberProfile(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));
    }

    public Region getHomeSummaryRegion(Long regionId) {
        return regionRepository.findById(regionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 지역을 찾을 수 없습니다."));
    }

    public Member joinMember(MemberRequestDTO.SignUpRequest request) {
        // request로 들어온 password 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 컨버터로 분리, 단일책임원칙
        return MemberConverter.toMember(request, encodedPassword);
    }
}
