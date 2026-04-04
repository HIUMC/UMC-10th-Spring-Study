package me.moonkyuong.springstart.hello.core;

import me.moonkyuong.springstart.hello.core.member.Grade;
import me.moonkyuong.springstart.hello.core.member.Member;
import me.moonkyuong.springstart.hello.core.member.MemberService;
import me.moonkyuong.springstart.hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
