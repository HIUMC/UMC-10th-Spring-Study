package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "itemA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

<<<<<<< HEAD:timerrrr/스프링 핵심 원리 - 기본편/core/src/test/java/hello/core/order/OrderServiceTest.java

=======
>>>>>>> f0dcabec7b03d0fc9f4ea14c6ad4403c4b8334cb:sophia22001/스프링 핵심 원리 - 기본편/core/src/test/java/hello/core/order/OrderServiceTest.java
}
