package com.example.umt10th.global.security.util;

import com.example.umt10th.domain.member.enums.SocialType;
import com.example.umt10th.global.security.entity.AuthMember;
import com.example.umt10th.global.security.entity.OAuthMember;
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
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final Duration accessExpiration;


    public JwtUtil(
            @Value("${jwt.token.secretKey}") String secret,
            @Value("${jwt.token.expiration.access}") Long accessExpiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessExpiration = Duration.ofMillis(accessExpiration);
    }

    /***
     * accessToken 생성
     * @param member
     * @return
     */
    public String createAccessToken(AuthMember member){
        return createToken(member, accessExpiration);
    }

    /***
     * 토큰에서 이메일 가져오기
     *
     * @param token 유저 정보를 추출할 토큰
     * @return 유저 이메일을 토큰에서 추출한다.
     */
    public String getEmail(String token){
        try {
            return getClaims(token).getPayload().getSubject();
        } catch (JwtException e){
            return null;
        }
    }

    /***
     * 토큰에서 Uid 가져오기
     *
     * @param token 유저 정보를 추출할 토큰
     * @return 유저 Uid을 토큰에서 추출한다.
     */
    public String getUid(String token){
        try {
            return getClaims(token).getPayload().getSubject();
        } catch (JwtException e){
            return null;
        }
    }

    /***
     * 토큰 유효성 확인
     *
     * @param token 유효한지 확인할 토큰
     * return True, False 반환
     */
    public boolean isValid(String token){
        try {
            getClaims(token);
            return true;
        } catch (JwtException e){
            return false;
        }
    }


    /***
     * 토큰 생성
     */
    public String createToken(AuthMember member, Duration expiration){
        Instant now = Instant.now();

        // 인가 정보 생성
        String authorities = member.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

//        // 폼 로그인 기반 JWT 토큰
//        return Jwts.builder()
//                .subject(member.getUsername()) // User 이메일을 Subject로
//                .claim("role", authorities) // 역할 정보
//                .claim("email", member.getUsername()) // User 이메일
//                .issuedAt(Date.from(now)) // 발급시간
//                .expiration(Date.from(now.plus(expiration))) // 만료시간
//                .signWith(secretKey) // 서버 내 시크릿 키로 서명
//                .compact(); // eyJhbGciOiJIUz... 형태의 길고 복잡한 하나의 문자열(String) 토큰

        // 소셜 로그인 기반 JWT 토큰
        return Jwts.builder()
                .subject(member.getUsername())
                .claim("role", authorities)
                .claim("social_type", member.getMember().getSocialType())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(expiration)))
                .signWith(secretKey)
                .compact();
    }

    /***
     * 토큰 정보 가져오기
     */
    private Jws<Claims> getClaims(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(secretKey)
                .clockSkewSeconds(60)
                .build()
                .parseSignedClaims(token);
    }

    /***
     * 토큰에서 소셜 로그인 타입 가져오기
     */
    public SocialType getSocialType(String token){
        try {
            return SocialType.valueOf(getClaims(token).getPayload().get("social_type").toString().toUpperCase());
        } catch (JwtException e){
            return null;
        }
    }
}
