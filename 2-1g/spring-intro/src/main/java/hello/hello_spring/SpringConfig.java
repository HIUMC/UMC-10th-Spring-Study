package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/// Component Scan 방식이 아닌 직접 Spring bean에 객체 등록하기
/// Controller는 항상 Component Scan 방식 사용
/// 보통 Component Scan 방식 중 생성자 주입 방식을 사용하지만
/// 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 Spring Bean에 등록한다
/// ex) MemoryMemberRepository가 아닌 실제 DbRepository로 교체
@Configuration
public class SpringConfig {

    /// Bean 어노테이션을 사용해서 Spring Bean에 객체 등록
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
