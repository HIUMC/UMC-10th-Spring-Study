package spring_core.demo;

import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_core.demo.discount.DiscountPolicy;
import spring_core.demo.discount.FixDiscountPolicy;
import spring_core.demo.discount.RateDiscountPolicy;
import spring_core.demo.member.MemberRepository;
import spring_core.demo.member.MemberService;
import spring_core.demo.member.MemberServiceImpl;
import spring_core.demo.member.MemoryMemberRepository;
import spring_core.demo.order.OrderService;
import spring_core.demo.order.OrderServiceImpl;


@Configuration //스프링컨테이너에 빈이 등록됨
public class AppConfig { //앱 환경설정? 관심사의 분리
//여기만 바꾸면 됨 이제

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); //생성자로 설정
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
         return new RateDiscountPolicy();
    }

}
