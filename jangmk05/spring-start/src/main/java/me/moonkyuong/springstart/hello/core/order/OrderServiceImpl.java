package me.moonkyuong.springstart.hello.core.order;

import me.moonkyuong.springstart.hello.core.discount.DiscountPolicy;
import me.moonkyuong.springstart.hello.core.discount.FixDiscountPolicy;
import me.moonkyuong.springstart.hello.core.member.Member;
import me.moonkyuong.springstart.hello.core.member.MemberRepository;
import me.moonkyuong.springstart.hello.core.member.MemoryMemberRespository;

public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository = new MemoryMemberRespository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
