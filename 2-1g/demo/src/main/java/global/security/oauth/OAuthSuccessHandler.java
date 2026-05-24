package global.security.oauth;

import com.example.demo.domain.member.dto.MemberResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.apiPayload.ApiResponse;
import global.security.AuthMember;
import global.security.CustomUserDetailsService;
import global.security.jwt.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        Number memberId = oauth2User.getAttribute("memberId");

        UserDetails userDetails = customUserDetailsService.loadUserByMemberId(memberId.longValue());
        AuthMember authMember = (AuthMember) userDetails;

        String accessToken = jwtUtil.createAccessToken(authMember);

        MemberResponseDTO.LoginResultDTO result = MemberResponseDTO.LoginResultDTO.builder()
                .memberId(authMember.getMemberId())
                .accessToken(accessToken)
                .tokenType("Bearer")
                .build();

        response.setContentType("application/json;charset=UTF-8");
        objectMapper.writeValue(response.getWriter(), ApiResponse.onSuccess(result));
    }
}
