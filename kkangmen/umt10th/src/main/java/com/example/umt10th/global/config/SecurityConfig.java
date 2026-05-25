package com.example.umt10th.global.config;

import com.example.umt10th.global.security.entity.CustomAccessDenied;
import com.example.umt10th.global.security.entity.CustomEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    // 허용 url
    private final String[] allowUris = {
            "/swagger-ui/**", // 스웨거
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/auth/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        .permitAll())
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
}
