package umc10th.assignment.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc10th.assignment.global.security.entity.AuthMember;
import umc10th.assignment.global.security.util.JwtUtil;
import umc10th.assignment.review.repository.ReviewRepository;
import umc10th.assignment.user.code.UserErrorCode;
import umc10th.assignment.user.dto.UserJoinRequest;
import umc10th.assignment.user.dto.UserRequestDto;
import umc10th.assignment.user.dto.UserResponseDto;
import umc10th.assignment.user.entity.User;
import umc10th.assignment.user.exception.UserException;
import umc10th.assignment.user.repository.UserRepository;

// UserService

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final JwtUtil jwtUtil;
    public UserResponseDto.Login login(UserRequestDto.Login request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new UserException(UserErrorCode.INVALID_PASSWORD);
        }

        AuthMember authMember = new AuthMember(user);
        String accessToken = jwtUtil.createAccessToken(authMember);

        return UserResponseDto.Login.builder()
                .accessToken(accessToken)
                .build();
    }
    // 마이페이지
    public UserResponseDto.GetInfo getInfo(AuthMember member) {

        User user = member.getUser(); // 또는 member.getMember()

        return UserResponseDto.GetInfo.builder()
                .name(user.getNickname())
                .profileUrl("https://example.com/profile.png")
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .point(user.getPoint())
                .build();
    }

    // 내 포인트 조회
    public UserResponseDto.GetPoint getPoint(AuthMember member) {

        User user = member.getUser(); // 또는 member.getMember()

        return UserResponseDto.GetPoint.builder()
                .point(user.getPoint())
                .build();
    }

    public Long join(UserJoinRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserException(UserErrorCode.USER_ALREADY_EXISTS);
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .email(request.getEmail())
                .password(encodedPassword).nickname(request.getName()).point(0)
                .build();

        return userRepository.save(user).getMemberId();
    }
}