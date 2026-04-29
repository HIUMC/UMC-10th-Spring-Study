package hello.core.singleton;

public class SingletonService {

    // static: 클래스에 하나만 생성되고 모든 곳에서 공유된다.
    // final: 한번 할당되면 값 변경이 불가능하다.
    private static final SingletonService instance = new SingletonService();

    // 싱글톤은 생성자가 private !! 다른 곳에서 생성할 수 없다.
    private SingletonService() {
    }

    // 이미 만들어진 객체를 재사용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
