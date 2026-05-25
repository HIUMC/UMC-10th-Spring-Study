package global.config;

import global.security.CustomUserDetailsService;
import global.security.jwt.JwtAuthFilter;
import global.security.jwt.JwtUtil;
import global.security.oauth.CustomOAuth2UserService;
import global.security.oauth.OAuthFailureHandler;
import global.security.oauth.OAuthSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final global.security.handler.CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final global.security.handler.CustomAccessDeniedHandler accessDeniedHandler;

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuthSuccessHandler oauthSuccessHandler;
    private final OAuthFailureHandler oauthFailureHandler;

    // 인증인가 과정 없이 호용할 URL
    private final String[] allowUris = {
            // Swagger 허용
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/auth/**",
            "/oauth2/**",
            "/login/oauth2/**",
            "/login/**"
    };
    private final String[] publicAPI = {
            "/auth/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .addFilterBefore(
                        new JwtAuthFilter(jwtUtil, customUserDetailsService),
                        UsernamePasswordAuthenticationFilter.class
                )
                .oauth2Login(oauth -> oauth
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                        .successHandler(oauthSuccessHandler)
                        .failureHandler(oauthFailureHandler)
                )
                .csrf(AbstractHttpConfigurer::disable) // CSRF 공격 방어 비활성화
                .authorizeHttpRequests(requests -> requests
                        // allowUris, publicAPI 들은 모두 허용함
                        .requestMatchers(allowUris).permitAll()
                        .requestMatchers(publicAPI).permitAll()
                        .anyRequest().authenticated() // 그걸 제외하곤 무조건 인증 검사
                )
                /*.formLogin(form -> form
                        // 로그인 성공 시 defaultSuccessUrl로 리다이렉트
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        .permitAll() // 모든 사용자 접근 가능
                )*/
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

}
