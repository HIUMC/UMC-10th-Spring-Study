package me.moonkyuong.springintro.service;

import me.moonkyuong.springintro.repository.JdbcMemberRepository;
import me.moonkyuong.springintro.repository.JdbcTemplateMemberRepository;
import me.moonkyuong.springintro.repository.MemberRepository;
import me.moonkyuong.springintro.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
