package hello.core.singleton;

public class StatefulService {

    private int price;
    // 상태를 유지하는 필드. 공유되기 때문에 값이 변경되는 문제가 발생할 수 있다.
    // -> 스프링은 무상태로 설계해야함!

    public int order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        this.price = price; // 여기서 문제 !
        return price;
    }

    public int getPrice() {
        return price;
    }
}
