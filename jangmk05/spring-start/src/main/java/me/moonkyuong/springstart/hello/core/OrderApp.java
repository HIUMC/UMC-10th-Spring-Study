package me.moonkyuong.springstart.hello.core;

import me.moonkyuong.springstart.hello.core.member.Grade;
import me.moonkyuong.springstart.hello.core.member.Member;
import me.moonkyuong.springstart.hello.core.member.MemberService;
import me.moonkyuong.springstart.hello.core.member.MemberServiceImpl;
import me.moonkyuong.springstart.hello.core.order.Order;
import me.moonkyuong.springstart.hello.core.order.OrderService;
import me.moonkyuong.springstart.hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService =
                applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService",
                OrderService.class);
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
    }
}
