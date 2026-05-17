package com.example.umc10th.global.config;

import com.example.umc10th.global.security.exception.CustomAccessDenied;
import com.example.umc10th.global.security.exception.CustomEntryPoint;
import com.example.umc10th.global.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Spring Security 설정을 활성화하는 어노테이션
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    /**
     * 인증 없이 접근을 허용할 URL 목록
     * Swagger 화면, API 문서, 로그인/회원가입 관련 경로는 로그인하지 않아도 접근 가능하게 설정
     */
    private final String[] allowUris = {
            //Swagger 허용
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/auth/**"
    };

    /**
     * Spring Security의 필터 체인을 설정하는 Bean
     * 어떤 요청을 허용할지, 로그인 방식은 어떻게 할지 등을 여기서 정함
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 보호 기능을 비활성화
        // 보통 REST API 서버에서는 세션 기반 화면 요청이 아니기 때문에 꺼두는 경우가 많음
        http.csrf(AbstractHttpConfigurer::disable)
                // 요청별 접근 권한 설정
                .authorizeHttpRequests(requests -> requests
                        // allowUris에 등록된 경로들은 인증 없이 접근 가능
                        .requestMatchers(allowUris).permitAll()
                        // 위에서 허용한 경로를 제외한 나머지 모든 요청은 로그인한 사용자만 접근 가능
                        .anyRequest().authenticated())
                // formLogin은 기본 로그인 폼 방식을 사용하겠다는 의미
                // 즉, Spring Security가 제공하는 기본 로그인 페이지를 사용할 수 있음
                .formLogin(form -> form
                        // 로그인 성공 후 이동할 기본 URL
                        // true를 주면 이전 요청 URL이 있더라도 무조건 Swagger 화면으로 이동
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        // 로그인 페이지 자체는 인증 없이 접근 가능해야 하므로 허용
                        .permitAll())
                // 로그아웃 설정
                .logout(logout -> logout
                        // 로그아웃 요청을 보낼 URL
                        // 사용자가 /logout으로 요청하면 로그아웃 처리됨
                        .logoutUrl("/logout")
                        // 로그아웃 성공 후 이동할 URL
                        .logoutSuccessUrl("/login?logout")
                        // 로그아웃 요청도 인증 여부와 관계없이 접근 가능하도록 허용
                        .permitAll())
                // 예외 상황 핸들러
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDenied())
                        .authenticationEntryPoint(customEntryPoint()));

        // 위에서 설정한 내용을 기반으로 SecurityFilterChain 객체 생성
        return http.build();
    }

    /**
     * 비밀번호 암호화를 위한 PasswordEncoder Bean 등록
     * 회원가입 시 비밀번호를 암호화하고, 로그인 시 입력 비밀번호와 암호화된 비밀번호를 비교할 때 사용
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt는 Spring Security에서 많이 사용하는 안전한 단방향 비밀번호 암호화 방식
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
