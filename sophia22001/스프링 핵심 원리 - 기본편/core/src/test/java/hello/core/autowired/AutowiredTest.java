package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class); // TestBean이 스프링 빈으로 등록됨
    }

    // 임의의 테스트 클래스
    static class TestBean {

        // 메서드 호출 자체가 안된다.
        @Autowired(required = false) // false 지정 안하면 UnsatisfiedDependencyException 오류난다.
        public void setNoBean1(Member noBean1) { // 스프링 빈이 아닌 Member
            System.out.println("noBean1 = " + noBean1);
        }

        // 호출은 되는데 null
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // 호출은 되는데 Optional.empty
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
