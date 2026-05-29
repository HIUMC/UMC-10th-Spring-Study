package com.example.mission4.global.config;

import com.example.mission4.global.security.exception.CustomAccessDenied;
import com.example.mission4.global.security.exception.CustomEntryPoint;
import com.example.mission4.global.security.filter.JwtAuthFilter;
import com.example.mission4.global.security.handler.CustomOAuthService;
import com.example.mission4.global.security.handler.OAuthSuccessHandler;
import com.example.mission4.global.security.service.CustomUserDetailsService;
import com.example.mission4.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity // Spring Security 설정을 활성화
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomOAuthService customOAuthService;

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
    }

    @Bean
    public OAuthSuccessHandler oAuthSuccessHandler() {
        return new OAuthSuccessHandler(jwtUtil);
    }

    // 인증 없이 접근 가능한 경로
    private final String[] allowUris = {
            // swagger 허용
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
    };

    // 공개 API 경로
    private final String[] publicAPI = {
            "/auth/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter, CustomOAuthService customOAuthService) throws Exception {
            http
                    .csrf(AbstractHttpConfigurer::disable) // CSRF 비활성화
                    // URI 허용 여부
                    .authorizeHttpRequests(requests -> requests
                                .requestMatchers(allowUris).permitAll()
                                .requestMatchers(publicAPI).permitAll()
                                .anyRequest().authenticated()) // 그 외 모든 요청 → 반드시 인증 필요
                    // 폼 로그인
                    .formLogin(form -> form
                            .defaultSuccessUrl("/swagger-ui/index.html", true) // 로그인 성공 시 → Swagger UI로 리다이렉트
                            .permitAll()) // 로그인 페이지 자체는 누구나 접근 가능
                    .oauth2Login(oauth -> oauth
                            // 인증 엔트리 포인트
                            .authorizationEndpoint(auth -> auth.
                                    baseUri("/oauth/authorize"))
                            // 콜백 주소
                            .redirectionEndpoint(redirect -> redirect
                                    .baseUri("/oauth/callback/**"))
                            // 인증 완료 후 정보 활용
                            .userInfoEndpoint(userInfo -> userInfo
                                    .userService(customOAuthService))
                            // 성공 시 JWT 토큰을 발행할 핸들러
                            .successHandler(oAuthSuccessHandler())
                            .failureHandler((request, response, exception) -> {
                                // 실제 에러 확인용 - 콘솔에 출력
                                exception.printStackTrace();
                                response.setStatus(400);
                                response.getWriter().write("OAuth2 Error: " + exception.getMessage());
                            }))
                    // 세션
                    .sessionManagement(AbstractHttpConfigurer::disable)
                    // JWT 필터
                    .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                    // 로그아웃
                    .logout(logout -> logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login?logout") // 로그아웃 성공 시 → /login?logout 으로 리다이렉트
                            .permitAll())
                    // 인증 실패 에러 통일 객체 주입
                    .exceptionHandling(exception -> exception
                            .accessDeniedHandler(customAccessDenied())
                            .authenticationEntryPoint(customEntryPoint()));

            return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호를 BCrypt 해시 알고리즘으로 암호화
    }

    /**
     * 인증 실패 에러 통일 관련 객체들을 빈으로 등록한다.
     * CustomAccessDenied
     * CustomEntryPoint
     */
    @Bean
    public CustomAccessDenied customAccessDenied() {
        return new CustomAccessDenied();
    }

    @Bean
    public CustomEntryPoint customEntryPoint() {
        return new CustomEntryPoint();
    }
}
