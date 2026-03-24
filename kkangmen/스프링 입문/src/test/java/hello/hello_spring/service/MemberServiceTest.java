package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.JpaMemberRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.repository.SpringDataJpaMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberRepository memberRepository = new JpaMemberRepository();
    MemberService memberService = new MemberService(memberRepository);

//    @AfterEach
//    void afterEach(){
//        memberRepository.clearStore();
//    }

    @Test
    void join() {
        // given
        Member member = new Member("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberRepository.findById(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복회원(){
        // given
        Member member1 = new Member("spring");
        Member member2 = new Member("spring");

        // when
        memberService.join(member1);

        // then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
}