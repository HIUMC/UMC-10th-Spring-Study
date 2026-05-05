package hello.core.autowired;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discount = discountService.discount(member, 10000, "fixDiscountPolicy");

        System.out.println("discount = " + discount);
    }

    @RequiredArgsConstructor
    static class DiscountService {

        private final Map<String, DiscountPolicy> policyMap;

        public int discount(Member member, int price, String discountCode){
            DiscountPolicy discountPolicy = policyMap.get(discountCode); // discountCode에 따라 구현체가 바뀐다.

            return discountPolicy.discount(member, price);
        }
    }
}
