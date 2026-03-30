package me.moonkyuong.springstart.hello.core;

import me.moonkyuong.springstart.hello.core.discount.DiscountPolicy;
import me.moonkyuong.springstart.hello.core.discount.FixDiscountPolicy;
import me.moonkyuong.springstart.hello.core.discount.RateDiscountPolicy;
import me.moonkyuong.springstart.hello.core.member.MemberRepository;
import me.moonkyuong.springstart.hello.core.member.MemberService;
import me.moonkyuong.springstart.hello.core.member.MemberServiceImpl;
import me.moonkyuong.springstart.hello.core.member.MemoryMemberRespository;
import me.moonkyuong.springstart.hello.core.order.OrderService;
import me.moonkyuong.springstart.hello.core.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }
    public MemberRepository memberRepository() {
        return new MemoryMemberRespository();
    }
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
