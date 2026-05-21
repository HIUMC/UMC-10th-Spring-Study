package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // 테스트에서는 실행 후 롤백을 한다.
class MemberServiceTest {


    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
//    @Rollback(false)
    public void 회원가입() throws Exception{

        Member member = new Member();
        member.setName("kim");

        Long saveId = memberService.join(member);

        Assertions.assertEquals(member, memberRepository.findById(saveId));

    }

    @Test
    public void 중복_회원_예외() throws Exception{
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);

        // then
        // IllegalStateException 예외가 발생하지 않으면 테스트 실패
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

}