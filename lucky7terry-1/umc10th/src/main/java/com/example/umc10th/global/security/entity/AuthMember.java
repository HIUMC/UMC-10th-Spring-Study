package com.example.umc10th.global.security.entity;

import com.example.umc10th.domain.member.entity.Member;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class AuthMember implements UserDetails {

    private final Member member;

    // 사용자의 권한 목록을 반환하는 메서드
    // 현재는 권한 설정을 따로 하지 않았기 때문에 빈 리스트 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // 로그인 검증에 사용할 비밀번호 반환
    // Member 엔티티에 저장된 암호화된 비밀번호를 반환함
    @Override
    public @Nullable String getPassword() {
        return member.getPassword();
    }

    // 로그인할 때 사용자를 식별하는 값 반환
    // 여기서는 username 대신 email을 로그인 ID로 사용
    @Override
    public String getUsername() {
        return member.getEmail();
    }
}
