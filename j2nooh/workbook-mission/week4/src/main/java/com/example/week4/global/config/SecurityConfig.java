package com.example.week4.global.config;

import com.example.week4.domain.user.security.CustomUserDetailsService;
import com.example.week4.global.handler.CustomAccessDeniedHandler;
import com.example.week4.global.handler.CustomAuthenticationEntryPoint;
import com.example.week4.global.security.filter.JwtAuthFilter;
import com.example.week4.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.week4.global.security.handler.OAuthSuccessHandler;
import com.example.week4.global.security.service.CustomOAuthService;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomOAuthService customOAuthService;

    private final String[] allowUris = {
            // Swagger 허용
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v3/api-docs/**",

            // 인증 관련 허용
            "/auth/**",
            "/oauth2/**",
            "/oauth/**",

            // 에러 응답 허용
            "/error"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                // URI 허용 여부
                .authorizeHttpRequests(requests -> requests
                        // Public API 허용
                        .requestMatchers(allowUris).permitAll()
                        // 그 외 API는 인증 필요
                        .anyRequest().authenticated()
                )
                // 폼 로그인 비활성화
                .formLogin(AbstractHttpConfigurer::disable)
                // HTTP Basic 비활성화
                .httpBasic(AbstractHttpConfigurer::disable)
                // JWT는 세션을 사용하지 않으므로 STATELESS 설정
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                // JWT 필터 등록
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                // 예외 상황 핸들러
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                .oauth2Login(oauth -> oauth
                        .authorizationEndpoint(endpoint -> endpoint
                                .baseUri("/oauth2/authorization")
                        )
                        .redirectionEndpoint(endpoint -> endpoint
                                .baseUri("/oauth/callback/*")
                        )
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuthService)
                        )
                        .successHandler(oAuthSuccessHandler())
                        .failureHandler(((request, response, exception) -> {
                            exception.printStackTrace();
                            response.setContentType("application/json;charset=UTF-8");
                            response.setStatus(401);
                            response.getWriter().write("""
                                    {
                                    "status": 401,
                                    "message": "%s",
                                    "exception":"%s"
                                    }
                                    """.formatted(
                                    exception.getMessage(),
                                    exception.getClass().getName()
                            ));
                        })
                        ));



        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
    }

    @Bean
    public OAuthSuccessHandler oAuthSuccessHandler() {
        return new OAuthSuccessHandler(jwtUtil);
    }
}
