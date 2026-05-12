package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @Rollback(false) // 롤백 안하고 커밋해버림
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("lee");

        //when
        Long savedId = memberService.join(member);

        //then
        Assertions.assertEquals(member, memberRepository.findOne(savedId));

    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("lee");

        Member member2 = new Member();
        member2.setName("lee");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2); // 예외 발생해야함
        } catch (IllegalStateException e) {
            return;
        }

        //then
        fail("예외가 발생함");

    }

}