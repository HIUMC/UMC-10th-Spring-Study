package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
// 값 타입은 변경 불가하게 설계.
public class Address {

    private String street;
    private String city;
    private String zipcode;

    protected Address() {} // 기본 생성자

    public Address(String street, String city, String zipcode) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
    }
}
