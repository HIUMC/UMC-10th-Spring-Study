package hello.hello_spring.config;

import hello.hello_spring.aop.TimeTraceAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
}
