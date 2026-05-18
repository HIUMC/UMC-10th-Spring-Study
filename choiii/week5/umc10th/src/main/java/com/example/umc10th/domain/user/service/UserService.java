package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.request.UserCreateRequest;
import com.example.umc10th.domain.user.dto.request.UserTermsRequest;
import com.example.umc10th.domain.user.dto.response.UserResponse;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.entity.UserTerms;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.domain.user.repository.UserTermsRepository;
import com.example.umc10th.global.exception.NotFoundException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserTermsRepository userTermsRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        if(userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("이미 사용 중인 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.password());
        User user = UserConverter.toEntity(request, encodedPassword);

        return UserConverter.toResponse(userRepository.save(user));
    }

    public UserResponse getUser(Long userId) {
        return UserConverter.toResponse(findUser(userId));
    }

    @Transactional
    public void agreeTerms(Long userId, UserTermsRequest request) {
        User user = findUser(userId);
        userTermsRepository.save(UserTerms.builder()
                .user(user)
                .requiredAgreed(request.requiredAgreed())
                .locationAgreed(request.locationAgreed())
                .marketingAgreed(request.marketingAgreed())
                .agreedAt(LocalDateTime.now())
                .build());
    }

    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다. userId=" + userId));
    }
}
