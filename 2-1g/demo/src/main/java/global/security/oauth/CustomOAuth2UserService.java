package global.security.oauth;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.enums.Gender;
import com.example.demo.domain.member.enums.SocialType;
import com.example.demo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oauth2User = super.loadUser(userRequest);

        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(oauth2User.getAttributes());

        String email = kakaoUserInfo.getEmail();
        String nickname = kakaoUserInfo.getNickname();

        Member member = memberRepository.findByEmail(email)
                .orElseGet(() -> memberRepository.save(
                        Member.builder()
                                .name(nickname)
                                .gender(Gender.MALE)
                                .birth(LocalDate.of(2000, 1, 1))
                                .address("OAuth 가입자")
                                .point(0L)
                                .agreement(true)
                                .socialLogin(SocialType.KAKAO)
                                .email(email)
                                .password(passwordEncoder.encode("OAUTH2_USER"))
                                .number("010-0000-0000")
                                .nickname(nickname)
                                .completionCount(0L)
                                .build()
                ));

        return new DefaultOAuth2User(
                List.of(() -> "ROLE_USER"),
                Map.of(
                        "email", member.getEmail(),
                        "nickname", member.getNickname()
                ),
                "email"
        );
    }
}
