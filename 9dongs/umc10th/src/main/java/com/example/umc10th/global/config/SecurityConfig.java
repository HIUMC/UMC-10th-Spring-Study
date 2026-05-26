package com.example.umc10th.global.config;

import com.example.umc10th.global.auth.CustomAccessDenied;
import com.example.umc10th.global.auth.CustomEntryPoint;
import com.example.umc10th.global.security.filter.JwtAuthFilter;
import com.example.umc10th.global.security.handler.OAuthSuccessHandler;
import com.example.umc10th.global.security.service.CustomOAuthService;
import com.example.umc10th.global.security.service.CustomUserDetailsService;
import com.example.umc10th.global.security.util.JwtUtil;
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

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomOAuthService customOAuthService;

    private final String[] allowUris = {
            // Swagger 허용
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/auth/**",
            "/api/auth/**",
            "/oauth/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                // URI 사용 여부
                .authorizeHttpRequests(requests -> requests
                        // Public API 허용
                        .requestMatchers(allowUris).permitAll()
                        // 그 이외 API는 인증 필요
                        .anyRequest().authenticated()
                )

                // 폼 로그인
                .formLogin(AbstractHttpConfigurer::disable)

                // JWT 필터
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                // OAuth
                .oauth2Login(oauth -> oauth
                    // 인증 엔트리 포인트
                    .authorizationEndpoint(auth -> auth
                            .baseUri("/oauth/authorize")
                    )
                    // 콜백 주소
                    .redirectionEndpoint(redirect -> redirect
                            .baseUri("/oauth/callback/**")
                    )
                    // 인증 완료 후 정보 활용
                    .userInfoEndpoint(userInfo -> userInfo
                            .userService(customOAuthService))
                    // 성공시 JWT 토큰 발행할 핸들러
                    .successHandler(oAuthSuccessHandler())

                    // 에러 출력용 커스텀 실패 핸들러
                    .failureHandler((request, response, exception) -> {
                        System.out.println(" 카카오 로그인 실패 원인 ");
                        exception.printStackTrace(); // 콘솔에 빨간 줄로 진짜 에러를 출력합니다.
                        response.sendError(401, "소셜 로그인 실패: " + exception.getMessage());
                    })
                )

                // 예외 상황 핸들러
                .exceptionHandling(exception -> exception
                         .accessDeniedHandler(customAccessDenied())
                         .authenticationEntryPoint(customEntryPoint())
                );

               ;


        return http.build();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
    }

    @Bean
    public OAuthSuccessHandler oAuthSuccessHandler() {
        return new OAuthSuccessHandler(jwtUtil);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAccessDenied customAccessDenied() {
        return new CustomAccessDenied();
    }

    @Bean
    public CustomEntryPoint customEntryPoint() {
        return new CustomEntryPoint();
    }
}