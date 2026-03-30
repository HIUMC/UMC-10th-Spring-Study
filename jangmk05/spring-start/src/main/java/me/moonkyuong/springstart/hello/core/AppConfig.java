package me.moonkyuong.springstart.hello.core;

import me.moonkyuong.springstart.hello.core.discount.FixDiscountPolicy;
import me.moonkyuong.springstart.hello.core.member.MemberService;
import me.moonkyuong.springstart.hello.core.member.MemberServiceImpl;
import me.moonkyuong.springstart.hello.core.member.MemoryMemberRespository;
import me.moonkyuong.springstart.hello.core.order.OrderService;
import me.moonkyuong.springstart.hello.core.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRespository());
    }
    public OrderService orderService() {
        return new OrderServiceImpl(
                new MemoryMemberRespository(),
                new FixDiscountPolicy());
    }
}
