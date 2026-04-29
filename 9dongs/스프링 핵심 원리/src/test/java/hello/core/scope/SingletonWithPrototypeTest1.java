package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.login();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.login();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {

        // 싱글톤 빈이 프로토타입을 사용할 때 마다 스프링 컨테이너에 새로 요청
//        @Autowired private ApplicationContext ac;
//        public int logic() {
//            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
//            prototypeBean.addCount();
//            int count = prototypeBean.getCount();
//            return count;
//        }

        // ObjectProvider
//        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
//
//        public int login() {
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); // 새로운 프로토타입 빈 생성, 대신 조회자(대리자)
//            prototypeBean.addCount();
//            return prototypeBean.getCount();
//        }

        // JSR-330 Provider
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int login() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get(); // 새로운 프로토타입 빈 생성, 대신 조회자(대리자)
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
