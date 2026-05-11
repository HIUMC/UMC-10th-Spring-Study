package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery") // 나(delivery)는 연관관계의 주인이 아니고, 주인의 거울이다.
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // EnumType.STRING: 숫자(ORDINAL)가 아닌 문자열로 enum 관리
    private DeliveryStatus status; // 배송 상태 [READY(준비중), COMP(배송중)]
}
