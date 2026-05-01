package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
<<<<<<< HEAD:timerrrr/스프링 핵심 원리 - 기본편/core/src/main/java/hello/core/discount/RateDiscountPolicy.java
public class RateDiscountPolicy implements DiscountPolicy {
=======
public class RateDiscountPolicy implements DiscountPolicy{
>>>>>>> f0dcabec7b03d0fc9f4ea14c6ad4403c4b8334cb:sophia22001/스프링 핵심 원리 - 기본편/core/src/main/java/hello/core/discount/RateDiscountPolicy.java

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
