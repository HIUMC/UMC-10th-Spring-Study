package umc10th.assignment.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc10th.assignment.review.repository.ReviewRepository;
import umc10th.assignment.user.code.UserErrorCode;
import umc10th.assignment.user.dto.UserJoinRequest;
import umc10th.assignment.user.dto.UserResponseDto;
import umc10th.assignment.user.entity.User;
import umc10th.assignment.user.exception.UserException;
import umc10th.assignment.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public UserResponseDto.MyPageDTO getMyPage(Long memberId) {
        User user = userRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Long reviewCount = reviewRepository.countByUserMemberId(memberId);

        return new UserResponseDto.MyPageDTO(
                user.getMemberId(),
                user.getNickname(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPoint(),
                reviewCount
        );
    }
    public Long join(UserJoinRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserException(UserErrorCode.USER_ALREADY_EXISTS);
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()

                .email(request.getEmail())
                .password(encodedPassword)

                .build();
        return userRepository.save(user).getMemberId();


}}