package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//스프링 빈에 등록하라는 뜻

/**
 * Spring 실행
 *
 * → SpringConfig 읽음
 *
 * → memberRepository() 실행
 *    → MemoryMemberRepository 객체 생성
 *
 * → memberService() 실행
 *    → 위에서 만든 repository를 넣어서 생성 ( 서비스는 멤버 레포지토리가 필요함 )
 *
 * → 둘 다 스프링 컨테이너에 저장
 */

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
