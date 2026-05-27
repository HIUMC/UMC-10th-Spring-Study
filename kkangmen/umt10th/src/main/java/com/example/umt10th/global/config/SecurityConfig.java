package com.example.umt10th.global.config;

import com.example.umt10th.global.security.exception.CustomAccessDenied;
import com.example.umt10th.global.security.exception.CustomEntryPoint;
import com.example.umt10th.global.security.filter.JwtAuthenticationFilter;
import com.example.umt10th.global.security.handler.OAuthSuccessHandler;
import com.example.umt10th.global.security.service.CustomOAuthService;
import com.example.umt10th.global.security.service.CustomUserDetailsService;
import com.example.umt10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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

    private final CustomOAuthService customOAuthService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OAuthSuccessHandler oAuthSuccessHandler;

    // 허용 url
    private final String[] allowUris = {
            "/swagger-ui/**", // 스웨거
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/auth/**",
            "/oauth/**" // 소셜로그인 리다이렉트 URI
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable) // 폼 로그인 x
                // JWT 필터
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                // OAuth
                .oauth2Login(oauth -> oauth
//                        // 인증 엔트리 포인트
//                        .authorizationEndpoint(auth -> auth
//                                .baseUri("/oauth/authorize"))
//                        // 콜백 주소
//                        .redirectionEndpoint(redirect -> redirect
//                                .baseUri("/login/oauth2/code/*"))
                        // 인증 완료 후 정보 활용
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuthService))
                        // 성공 시 JWT 토큰 발행할 핸들러
                        .successHandler(oAuthSuccessHandler))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // 로그아웃 성공 시 리다이렉트
                        .permitAll())
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDenied())
                        .authenticationEntryPoint(customEntryPoint()));
        return http.build();
    }

    // 비밀번호 솔트
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAccessDenied customAccessDenied(){
        return new CustomAccessDenied();
    }

    @Bean
    public CustomEntryPoint customEntryPoint(){
        return new CustomEntryPoint();
    }

    /***
     * AuthenticationManager 등록
     * AuthenticationConfiguration을 통해 스프링 시큐리티가 내부적으로 구성한
     * 최상위 AuthenticationManager를 가져와서 빈으로 노출시킨다.
     * AuthService에서 DI받기 위함. (id/pw를 검증해야해서)
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        return configuration.getAuthenticationManager();
    }
}
