package global.config;

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

    private global.security.handler.CustomAuthenticationEntryPoint authenticationEntryPoint;
    private global.security.handler.CustomAccessDeniedHandler accessDeniedHandler;

    // 인증인가 과정 없이 호용할 URL
    private final String[] allowUris = {
            // Swagger 허용
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/auth/**"
    };
    private final String[] publicAPI = {
            "/auth/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF 공격 방어 비활성화
                .authorizeHttpRequests(requests -> requests
                        // allowUris, publicAPI 들은 모두 허용함
                        .requestMatchers(allowUris).permitAll()
                        .requestMatchers(publicAPI).permitAll()
                        .anyRequest().authenticated() // 그걸 제외하곤 무조건 인증 검사
                )
                .formLogin(form -> form
                        // 로그인 성공 시 defaultSuccessUrl로 리다이렉트
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        .permitAll() // 모든 사용자 접근 가능
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 이 주소로 요청 보내면 로그아웃
                        .logoutSuccessUrl("/login?logout")
                        .permitAll() // 모든 사용자 접근 가능
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}