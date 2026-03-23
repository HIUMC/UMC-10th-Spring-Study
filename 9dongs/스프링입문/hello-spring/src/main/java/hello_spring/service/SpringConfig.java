package hello_spring.service;

import hello_spring.repository.MemberRepository;
import hello_spring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 장점 : 나중에 Db 연결시 코드 한줄만 수정하면 싹 연결됨.
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
