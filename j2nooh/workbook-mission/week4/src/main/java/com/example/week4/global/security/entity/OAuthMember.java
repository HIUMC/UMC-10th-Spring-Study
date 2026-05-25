package com.example.week4.global.security.entity;

import com.example.week4.domain.user.entity.User;
import com.example.week4.domain.user.security.AuthUserDetails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class OAuthMember implements OAuth2User {

    private final User user;
    private final Map<String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new AuthUserDetails(user).getAuthorities();
    }

    @Override
    public String getName() {
        return user.getEmail();
    }
}
