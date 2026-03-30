package me.moonkyuong.springstart.hello.core;

import me.moonkyuong.springstart.hello.core.discount.DiscountPolicy;
import me.moonkyuong.springstart.hello.core.discount.RateDiscountPolicy;
import me.moonkyuong.springstart.hello.core.member.MemberRepository;
import me.moonkyuong.springstart.hello.core.member.MemberService;
import me.moonkyuong.springstart.hello.core.member.MemberServiceImpl;
import me.moonkyuong.springstart.hello.core.member.MemoryMemberRespository;
import me.moonkyuong.springstart.hello.core.order.OrderService;
import me.moonkyuong.springstart.hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;

public class AppConfig {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRespository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
