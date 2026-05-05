package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifecycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        // @Bean으로 등록시에만
        // destroyMethod 기본값 : 추론 -> 종료 메서드는 따로 등록해주지 않아도 close, shutdown 같은거 찾아서 실행
        // 종료 안하려면 "" 빈 문자열로
        // @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient(){
            // 객체 생성 한 다음 수정자 주입을 통해 url이 존재하게 됨 -> 생성 단계에서 호출 하면 값이 없음.
            NetworkClient networkClient = new NetworkClient();

            // 스프링 빈 이벤트 라이프 사이클 ( 싱글톤 )
            // 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
