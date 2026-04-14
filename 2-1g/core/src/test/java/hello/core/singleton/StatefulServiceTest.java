package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // Thread A - A사용자가 10000원 주문
        statefulService1.order("userA", 10000);

        // Thread B - B사용자가 20000원 주문
        statefulService2.order("userB", 20000);

        // Thread A - 사용자A가 주문 금액 조회
        // 객체를 싱글톤으로 관리하기 때문에 당연히 A가 아닌 B의 가격이 나옴
        // !!- 싱글톤 객체는 stateful하게 관리하면 절대 안됨 -!!
        // !!- 스프링 빈은 항상 stateless하게 설계 -!!
        int price = statefulService1.getPrice();
        System.out.println("price of userA = " + price);
        Assertions.assertThat(price).isEqualTo(20000);
    }

    // 테스트용 Config
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}