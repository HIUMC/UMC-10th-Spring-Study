package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService()
    {
        //메모리 멤버 리포지토리 자동 호출
        //아래에 어떤 리포지토리가 호출되는지도 다 나와있게 !!
        return new MemberServiceImpl(memberRepository());
    }

    //나중에 DB 관련으로 바꾸고 싶으면 얘만 바꿔주면 됨 !
    public MemberRepository memberRepository()
    {
        return new MemoryMemberRepository();
    }
    public OrderService orderService()
    {
        return new OrderServiceImpl(new MemoryMemberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy()
    {
        return new FixDiscountPolicy();
    }
}
