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

    /// 스프링은 Bean을 싱글톤으로 관리해준다고 했는데,
    /// 코드를 보면 new를 통해 각 객체를 생성해주고 있는 것처럼 보인다
    /// 과연 싱글톤이 지켜질까? - ㅇㅇ
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    // Config 파일에 Bean으로 등록하기 위한 메서드를
    // static으로 선언하면 스프링이 개입해서 오버라딩일 불가하기 때문에
    // 싱글톤 로직을 주입할 수 없다
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 만약 할인 정책을 바꾸려면, 이 부분만 고치면 됨
    // OCP 원칙이 매우 잘 지켜짐
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
