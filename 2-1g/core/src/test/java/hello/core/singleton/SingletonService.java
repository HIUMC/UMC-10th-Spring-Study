package hello.core.singleton;

public class SingletonService {

    // 내부적으로 SingletonService 객체를 만들어 내부 instance에 참조를 넣어둠
    private static final SingletonService instance = new SingletonService();

    // getInstance 메서드를 정의하여 내부적으로 정의해둔 싱글톤 객체를 반환하도록 함
    public static SingletonService getInstance() {
        return instance;
    }

    // SingletonService 생성자를 private으로 설정하여 외부에서 new로 객체 생성하는 것을 막음
    private SingletonService() { }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
