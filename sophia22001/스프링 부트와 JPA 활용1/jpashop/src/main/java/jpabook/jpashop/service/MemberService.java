package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final이 붙은 필드에 대해 생성자 생성
public class MemberService {

    private final MemberRepository memberRepository;

    // 필드 주입 // 변경할 수 없어 테스트에 좋지 않음
//    @Autowired
//    private MemberRepository memberRepository;

    // 생성자 주입
    // -> @RequiredArgsConstructor 로 대체 가능
//    @Autowired // 생성자 하나면 생략가능
//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }

    // 회원 가입
    @Transactional
    public Long join(Member member) {
        // 중복 회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 같은 이름이 있으면 중복 회원
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 하나 조회
    public Member findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
