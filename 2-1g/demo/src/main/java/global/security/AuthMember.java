package global.security;

import com.example.demo.domain.member.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Getter
public class AuthMember implements UserDetails {
    private final Long memberId;
    private final String email;
    private final String password;

    public AuthMember(Member member) {
        this.memberId = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
    }

    // GrantedAuthority이거나 그 자식인 목록을 반환하는 getAuthroties 메서드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // 여기서 Username은 이름이나 닉네임이 아닌 로그인 식별자
    // 이 프로젝트에선 email로 로그인하므로 email을 반환함
    @Override
    public String getUsername() {
        return email;
    }
}
