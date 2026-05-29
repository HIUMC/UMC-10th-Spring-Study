package com.example.mission4.global.security.util;

import com.example.mission4.global.security.entity.AuthMember;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * JWT 토큰 관련 작업을 하는 유틸
 */
@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final Duration accessExpiration;

    // 초기화 및 설정
    public JwtUtil(
            @Value("${jwt.token.secretKey}") String secret,
            @Value("${jwt.token.expiration.access}") Long accessExpiration
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); // 암호화 알고이즘에 적합한 SecretKey 객체로 변환
        this.accessExpiration = Duration.ofMillis(accessExpiration);
    }

    // AccessToken 생성
    public String createAccessToken(AuthMember member) {
        return createToken(member, accessExpiration);
    }

    /**
     * 토큰에서 이메일 가져오기
     * @param token 유저 정보를 추출할 토큰
     * @return 유저 이메일을 토큰에서 추출합니다.
     */
    public String getEmail(String token) {
        try {
            // 파싱해서 subject 가져오기
            return getClaims(token).getPayload().getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    /**
     * 토큰 유효성 확인
     * @param token 유효한지 확인할 토큰
     * @return True, False 반환합니다.
     */
    // 파싱 과정에서 예외가 터지면 유효하지 않은 토큰 (false)로 판단한다.
    public boolean isValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // 토큰 생성
    private String createToken(AuthMember member, Duration expiration){
        Instant now = Instant.now();

        // 인가 정보
        String authorities = member.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // Claims 구성, 유저의 권한들을 토큰에 담기
        return Jwts.builder()
                .subject(member.getUsername()) // User 이메일은 Subject로
                .claim("role",authorities)
                .claim("email", member.getUsername())
                .issuedAt(Date.from(now)) // 언제 발근했는지
                .expiration(Date.from(now.plus(expiration))) // 언제까지 유효한지
                .signWith(secretKey) // sign할 Key
                .compact();
    }

    // 토큰 정보 가져오기
    private Jws<Claims> getClaims(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(secretKey) // 위변조를 막기 위해 서명을 검증
                .clockSkewSeconds(60) // 서버 간의 시간 오차 감안
                .build()
                .parseSignedClaims(token);
    }
}
