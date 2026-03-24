package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    //의존성 주입
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    /**
     * 회원가입
     */

    public Long join(Member member)
    {
        //같은 이름이 있는 중복 회원은 안된다 !
        validateDuplicateMember(member);//중복 회원 검증 -> by 메서드 추출
        memberRepository.save(member);
        //회원가입하면 아이디만 반환!
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());

        result.ifPresent(m ->
        {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     *전체 회원 조회
     */
    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId)
    {
        return memberRepository.findById(memberId);
    }
}
