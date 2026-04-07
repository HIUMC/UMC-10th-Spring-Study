package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component

public class MemberServiceImpl implements MemberService {

    //인터페이스만 가지고 있으면 오류남 !
    // 그러므로 구현체인 MemoryMemberRepository 끌고오기
    private final MemberRepository memberRepository;

    //생성자 호출로 변경
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository=memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
