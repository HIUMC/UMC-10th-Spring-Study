package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter // 값 타입은 변경이 불가능해야한다. @Setter X
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {}
}
