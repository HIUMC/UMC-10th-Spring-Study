package jpabook.jpashop.repository;

import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch { // 검색 로직을 위한

    private String memberName;
    private OrderStatus orderStatus;
}
