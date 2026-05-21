package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final PasswordEncoder passwordEncoder; // SecurityConfig에서 Bean으로 등록한 PasswordEncoder DI

    // 회원가입 - BCrypt로 비밀번호 솔트 처리
    @Transactional
    public void join(MemberReqDTO.JoinDTO request) {
        Member member = Member.builder()
                .name(request.getName() != null ? request.getName() : "")
                .birth(LocalDate.now())
                .address(request.getAddress() != null ? request.getAddress() : "")
                .email(request.getEmail())
                .phoneNumber("")
                .socialUid("")
                .socialType(SocialType.LOCAL)
                .point(0)
                .password(passwordEncoder.encode(request.getPassword())) // BCrypt 암호화
                .build();

        memberRepository.save(member);
    }

    public MemberResDTO.MyPageDTO getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        return MemberResDTO.MyPageDTO.builder()
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }

    public MissionResDTO.MissionPageDTO getMyMissions(Long memberId, Boolean complete, int page, int size) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        Page<MemberMission> memberMissions = memberMissionRepository
                .findByMemberAndComplete(member, complete,
                        PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id")));

        return MissionConverter.toMissionPageDTO(memberMissions);
    }
}