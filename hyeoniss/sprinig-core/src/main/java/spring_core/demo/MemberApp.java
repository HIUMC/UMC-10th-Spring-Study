package spring_core.demo;

import spring_core.demo.member.Grade;
import spring_core.demo.member.Member;
import spring_core.demo.member.MemberService;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
