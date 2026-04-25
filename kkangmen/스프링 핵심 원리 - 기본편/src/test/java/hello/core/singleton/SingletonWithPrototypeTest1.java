package hello.core.singleton;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean bean1 = applicationContext.getBean(SingletonBean.class);
        int count1 = bean1.logic();
        assertThat(count1).isEqualTo(1);

        SingletonBean bean2 = applicationContext.getBean(SingletonBean.class);
        int count2 = bean2.logic();
        assertThat(count2).isEqualTo(2);

    }

    @RequiredArgsConstructor
    static class SingletonBean {
        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    @Getter
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
