package hello.core;

import lombok.Getter;
import lombok.Setter;

// lombok이 대신 getter, setter를 만들어줌
@Setter
@Getter
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("이한결");

        String name = helloLombok.name;
        System.out.println("name = " + name);
    }
}
