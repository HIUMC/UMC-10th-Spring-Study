package me.moonkyuong.springstart.hello.core;

import me.moonkyuong.springstart.hello.core.member.Grade;
import me.moonkyuong.springstart.hello.core.member.Member;
import me.moonkyuong.springstart.hello.core.member.MemberService;
import me.moonkyuong.springstart.hello.core.member.MemberServiceImpl;
import me.moonkyuong.springstart.hello.core.order.Order;
import me.moonkyuong.springstart.hello.core.order.OrderService;
import me.moonkyuong.springstart.hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
    }
}
