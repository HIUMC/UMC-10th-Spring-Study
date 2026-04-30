package spring.core.order;

import org.junit.jupiter.api.Test;
import spring.core.discount.FixDiscountPolicy;
import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemoryMemberRepository;

public class OrderServiceImplTest {
    
    @Test
    void createOrder() {
        
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        org.assertj.core.api.Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
