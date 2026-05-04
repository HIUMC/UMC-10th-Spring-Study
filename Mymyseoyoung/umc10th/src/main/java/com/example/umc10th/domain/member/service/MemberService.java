package com.example.umc10th.domain.member.service;


import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.MemberErrorCode;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.usermission.entity.UserMission;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //정보 가져오기
    public MemberResponseDTO.GetInfo getInfo(MemberRequestDTO.GetInfo dto) {

        //DTO에서 유저 ID를 추출
        Long userId = dto.userId();

        //DB에서 해당 유저 ID로 데이터 조회
        Member user = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        // 컨버터를 이용해서 응답 DTO 생성 & return
        return MemberConverter.toGetInfo(user);
    }


    //회원가입
    public MemberResponseDTO.JoinResult join(MemberRequestDTO.Join request) {

        //DTO 엔티티로 변환
        Member newMember = MemberConverter.toMember(request);

        Member savedMember = memberRepository.save(newMember);

        return MemberConverter.toJoinResult(savedMember);
    }

    // 내 포인트 조회
    public MemberResponseDTO.GetMyPointInfo getMyPointInfo(@PathVariable Long memberId)
    {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetMyPointInfo(member);
    }

    // 마이페이지 조회
    public MemberResponseDTO.MemberProfileResponse getMyPage (@PathVariable Long memberId)
    {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberResponseDTO.MemberProfileResponse.builder()
                .nickname(member.getName())
                .profileUrl(member.getProfileUrl())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .phoneVerified(member.getPhoneNumber() != null)
                .point(member.getPoint())
                .build();
    }
}
