package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

public class RateDiscountPolicy implements DiscountPolicy{

    private static final int rateDiscountAmount = 10;

    @Override
    public int discount(Member member, int price) {

        if(member.getGrade() == Grade.VIP){
            return price * rateDiscountAmount/100;
        } else{
            return 0;
        }
    }
}
