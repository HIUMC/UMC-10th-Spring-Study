package com.example.mission4.global.security.entity;

import com.example.mission4.domain.member.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Member 엔티티를 감싸는 래퍼 클래스 -> DB 저장용이 아님
 * Spring Security가 인증 처리할 때 사용하는 객체
 */

@Getter
@RequiredArgsConstructor
public class AuthMember implements UserDetails {

    private final Member member;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }
}
