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
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

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
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        memberRepository.save(member);
    }

    @Transactional
    public MemberResDTO.Login login(MemberReqDTO.LoginDTO request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));
        return MemberResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }

    public MemberResDTO.MyPageDTO getMyPage(AuthMember authMember) {
        Member member = authMember.getMember();
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