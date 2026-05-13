package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

import hello.core.order.Order;
import hello.core.order.OrderService;


public class OrderApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

        MemberService service = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member a = new Member(1L, "a", Grade.VIP);
        service.join(a);

        Order order = orderService.createOrder(1L,"itemA",10000);
        System.out.println("order = " + order);

    }
}
