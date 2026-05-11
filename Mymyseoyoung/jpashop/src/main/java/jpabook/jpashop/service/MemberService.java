package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
//(readOnly = true) 얘 넣으면 데이터 변경이 안됨 !
public class MemberService {


    //field 를 인젝션해줌.
    //생성자 injection
    //변경할 일이 없으므로 final
    private final MemberRepository memberRepository;


    /**
     * 회원 가입
     */
    public Long join(Member member){
        //중복 회원을 검증하는 로직 .
        validateDuplicateMember(member);
        memberRepository.save(member);
        //이렇게 꺼내면 항상 값이 있다는게 보장이 됨.
        return member.getId();
    }

    //멤버의 이름을 유니크 제약조건으로 잡아라 !! ( 중복 회원 검증 )
    private void validateDuplicateMember(Member member){
//EXCEPTION
        List<Member> members = memberRepository.findByName(member.getName());
        if(!findMembers().isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }
    /**
     *
     전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     *단건 조회
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
