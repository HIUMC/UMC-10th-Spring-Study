package com.example.umc10th.domain.user.service;


import com.example.umc10th.domain.user.converter.MemberConverter;
import com.example.umc10th.domain.user.dto.MemberRequestDTO;
import com.example.umc10th.domain.user.dto.MemberResponseDTO;
import com.example.umc10th.domain.user.entity.Member;
import com.example.umc10th.domain.user.enums.MemberErrorCode;
import com.example.umc10th.domain.user.exception.MemberException;
import com.example.umc10th.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository userRepository;

    public MemberResponseDTO.GetInfo getInfo(MemberRequestDTO.GetInfo dto) {

        //DTO에서 유저 ID를 추출
        Long userId = dto.id();

        //DB에서 해당 유저 ID로 데이터 조회
        Member user = userRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.USER_NOT_FOUND));
        // 컨버터를 이용해서 응답 DTO 생성 & return
        return MemberConverter.toGetInfo(user);
    }
}
