package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    //자바 설정 클래스인 AppConfig 읽어서 스프링 컨테이너 생성 !

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        //모든 빈의 이름을 String 배열로 반환
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        //iter - 리스트나 배열에서 for문 자동 출력
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = "+ beanDefinitionName + "object = " + bean);

        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        //모든 빈의 이름을 String 배열로 반환
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        //iter - 리스트나 배열에서 for문 자동 출력
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition= ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = "+ beanDefinitionName + "object = " + bean);
            }
        }
    }
}
