package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/// DIP, OCP 원칙을 지키기 위해 의존성을 주입하는 별도의 파일이 있어야 함
/// 즉, 서비스 파일에 구현체를 주입하는 별도의 설정 파일
/// 애플리케이션 동작에 필요한 구현 객체를 생성, 생성자를 통해 주입 - DI

@Configuration
public class AppConfig {

    // Bean 어노테이션을 통해 스프링 컨테이너 빈에 인스턴스를 등록
    // Bean은 기본적으로 Bean이 붙은 메서드의 이름을 이름으로 한다
    // 또는 @Bean("leehan") 과 같이 이름을 지어줄 수 있다
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 만약 할인 정책을 바꾸려면, 이 부분만 고치면 됨
    // OCP 원칙이 매우 잘 지켜짐
    @Bean
    public static DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
