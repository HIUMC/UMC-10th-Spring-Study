package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

/// Service 파일은 Repository와 다르게
/// 비즈니스와 가까운 용어로 함수를 만드는게 좋음
/// command+shift+t로 test파일을 바로 만들 수 있음
public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /// 회원가입
    /// 조건: 같은 이름의 회원 불가능
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증 메서드

        memberRepository.save(member);
        return member.getId();
    }

    /// NULL 반환 가능성이 있는 경우 Optional로 감싼다
    /// ifPresent 같은 메서드 활용 가능
    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> { // ifPresent: NULL이 아닌 값이 있다면 실행
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        });

        /* // 아래같이 구현도 가능함
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 이름입니다.");
                        });
        */
    }

    ///  전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
