package hello.spring_hello;


import hello.spring_hello.repository.MemberRepository;
import hello.spring_hello.repository.MemoryMemberRepository;
import hello.spring_hello.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
