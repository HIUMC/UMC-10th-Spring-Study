package com.example.week4.domain.user.service;

import com.example.week4.domain.user.converter.UserConverter;
import com.example.week4.domain.user.dto.UserReqDTO;
import com.example.week4.domain.user.dto.UserResDTO;
import com.example.week4.domain.user.entity.User;
import com.example.week4.domain.user.enums.Gender;
import com.example.week4.domain.user.enums.SocialProvider;
import com.example.week4.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResDTO.SignUpResponse signUp(UserReqDTO.SignUpRequest dto) {
        String encodedPassword = passwordEncoder.encode(dto.password());

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(encodedPassword)
                .birthDate(dto.birthDate())
                .address(dto.address())
                .gender(Gender.valueOf(dto.gender()))
                .phoneNumber(dto.phoneNumber())
                .phoneVerified(false)
                .userPoint(0)
                .socialProvider(SocialProvider.LOCAL)
                .socialLoginId(dto.email())
                .build();

        User savedUser = userRepository.save(user);

        return UserConverter.toSignUpResponse(savedUser);
    }

    public UserResDTO.MyPageResponse getMyPage(UserReqDTO.MyPageRequest dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        return UserConverter.toMyPageResponse(user);
    }

    @Transactional
    public UserResDTO.MyPageResponse updateMyPage(UserReqDTO.UpdateMyPageRequest dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        user.updateName(dto.name());

        return UserConverter.toMyPageResponse(user);
    }
}