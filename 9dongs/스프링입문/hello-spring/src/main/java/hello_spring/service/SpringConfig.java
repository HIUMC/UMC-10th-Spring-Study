package hello_spring.service;

import hello_spring.aop.TimeTraceAop;
import hello_spring.repository.JdbcMemberRepository;
import hello_spring.repository.JpaMemberRepository;
import hello_spring.repository.MemberRepository;
import hello_spring.repository.MemoryMemberRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// 장점 : 나중에 Db 연결시 코드 한줄만 수정하면 싹 연결됨.
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

//     @Bean
//     public MemberRepository memberRepository() {
//
//         return new MemoryMemberRepository(); : 다른 코드 수정 안하고 SpringConfig를 고치는 것 만으로 DI 변경 가능
//         return new JdbcMemberRepository(dataSource);
//         객체지향 다향성 활용 -> 개방 폐쇄 원칙 (OCP) ( 기능을 변경해도 기존 코드 변경 x = 확장은 O, 변경,수정은 X )
//         return new JdbcMemberRepository(dataSource);
//         return new JpaMemberRepository(em);
//     }
}
