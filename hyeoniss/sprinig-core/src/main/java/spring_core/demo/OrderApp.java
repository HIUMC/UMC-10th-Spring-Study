package spring_core.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_core.demo.member.Grade;
import spring_core.demo.member.Member;
import spring_core.demo.member.MemberService;
import spring_core.demo.member.MemberServiceImpl;
import spring_core.demo.order.Order;
import spring_core.demo.order.OrderService;
import spring_core.demo.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {

        // MemberService memberService = new MemberServiceImpl();
        // OrderService orderService = new OrderServiceImpl();

//        AppConfig appConfig = new AppConfig(); //생성자버전
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);

    }
}
