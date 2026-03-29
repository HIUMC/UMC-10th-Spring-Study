package hello.spring_basic.order;

import hello.spring_basic.member.Member;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
