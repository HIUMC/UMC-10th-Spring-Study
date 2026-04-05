package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("memberService2") // Bean 이름 수정 가능
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    // OCP 원칙 준수를 위한 생성자를 통한 구현체주입 - 생성자주입

    // ComponentScan을 사용하면 클래스에 의존관계가 있는 객체가 주입되지 않더라도
    // 일단 Bean으로 등록되기 때문에 Autowired라는 어노테이션을 통해
    // 의존성을 주입해주어야 함
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // fot Test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
