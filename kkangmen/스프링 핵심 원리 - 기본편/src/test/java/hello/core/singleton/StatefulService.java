package hello.core.singleton;

// 스프링 빈을 주입할 때 지역변수를 지양해야 한다.
public class StatefulService {

    private int price; // 상태를 유지하는 필드

    public void order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    public int getPrice(){
        return price;
    }
}
