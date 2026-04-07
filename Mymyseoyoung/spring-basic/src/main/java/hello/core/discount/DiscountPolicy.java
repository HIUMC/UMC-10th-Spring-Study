package hello.core.discount;

import hello.core.member.Member;
public interface DiscountPolicy {

    /**
     * 주문 서비스에서 할인을 적용한 금액을 계산합니다.
     * @param member
     * @param price
     * @return 할인 대상 금액
     */

    int discount(Member member,int price);
}
