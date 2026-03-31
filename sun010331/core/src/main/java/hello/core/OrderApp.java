package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {

        MemberService service = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member a = new Member(1L, "a", Grade.VIP);
        service.join(a);

        Order order = orderService.createOrder(1L,"itemA",10000);
        System.out.println("order = " + order);

    }
}
