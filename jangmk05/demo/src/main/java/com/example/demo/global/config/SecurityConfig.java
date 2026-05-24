package com.example.demo.global.config;

import com.example.demo.domain.member.service.CustomUserDetailsService;
import com.example.demo.global.apiPayload.handler.CustomAccessDenied;
import com.example.demo.global.apiPayload.handler.CustomEntryPoint;
import com.example.demo.global.security.filter.JwtAuthFilter;
import com.example.demo.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity // Spring Security 설정을 활성화 시키는 역할 -> Spring Security의 기본설정보다 우선시됨
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    // 스웨거 문서 페이지에서 인증없이 열어두는 예외 URI 모음
    private final String[] allowUris = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/auth/**"
    };

    // 인증이 아직 안 된 사용자도 접근해야 하는 실제 비즈니스 API 들
    private final String[] publicAPI = {
            "/api/auth/**",
    };

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests // http요청에 대한 접근 제어 설정
                        .requestMatchers(allowUris).permitAll() // requestMatchers : 특정 URL 패턴에 대한 접근 권한 설정
                        .requestMatchers(publicAPI).permitAll() // permitAll : 인증 없이 접근 가능한 경로를 지정
                        .anyRequest().authenticated() // 그외 모든 요청에 대해 인증을 요구
                )
                // 폼 기반 로그인에 대한 설정
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                // 로그아웃 처리에 대한 설정
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDenied())
                        .authenticationEntryPoint(customEntryPoint())
                )
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 비밀번호 솔트를 위한 BCrypt를 PasswordEncoder로 설정
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
