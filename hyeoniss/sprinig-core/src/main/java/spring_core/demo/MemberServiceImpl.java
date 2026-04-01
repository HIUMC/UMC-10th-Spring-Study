package spring_core.demo;

import spring_core.demo.member.Member;
import spring_core.demo.member.MemberRepository;
import spring_core.demo.member.MemberService;
import spring_core.demo.member.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
