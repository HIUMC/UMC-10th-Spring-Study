package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable // JPA의 내장타입
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    public Address(String city, String street, String zipcode){
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }


    // JPA 스펙상 엔티티나 임베디드 타입은 기본 생성자가 필요 // public 이나 protected로 열어두어야함
    protected Address() {
    }
}
