package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
// lombok
//@AllArgsConstructor // 생성자를 자동으로 만들어줌
//@RequiredArgsConstructor // final 키워드가 있는 객체에 대한 생성자만 만들어줌 Good
public class MemberService {

    // 필드 주입 - 객체를 바꾸지 못함
    @Autowired
    private MemberRepository memberRepository;


    // final 키워드가 있어야 컴파일 시점에 의존성 문제 해결 가능
    /*private final MemberRepository memberRepository;*/
    // 생성자 주입 - 객체를 바꿀 수 있음
    /*public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    // 회원 가입
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 회원 검증
    private void validateDuplicateMember(Member member) {
        if (!memberRepository.findByName(member.getName()).isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회 - 읽기 전용 트랜잭션
    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

}
