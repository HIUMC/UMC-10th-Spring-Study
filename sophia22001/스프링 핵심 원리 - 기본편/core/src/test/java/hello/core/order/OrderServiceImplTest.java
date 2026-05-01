package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

<<<<<<< HEAD:timerrrr/스프링 핵심 원리 - 기본편/core/src/test/java/hello/core/order/OrderServiceImplTest.java
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

=======
import static org.junit.jupiter.api.Assertions.*;

// 스프링 없이 자바 코드로 테스트
>>>>>>> f0dcabec7b03d0fc9f4ea14c6ad4403c4b8334cb:sophia22001/스프링 핵심 원리 - 기본편/core/src/test/java/hello/core/order/OrderServiceImplTest.java
class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
<<<<<<< HEAD:timerrrr/스프링 핵심 원리 - 기본편/core/src/test/java/hello/core/order/OrderServiceImplTest.java
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
=======
        memberRepository.save(new Member(1L, "nameA", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

>>>>>>> f0dcabec7b03d0fc9f4ea14c6ad4403c4b8334cb:sophia22001/스프링 핵심 원리 - 기본편/core/src/test/java/hello/core/order/OrderServiceImplTest.java
}