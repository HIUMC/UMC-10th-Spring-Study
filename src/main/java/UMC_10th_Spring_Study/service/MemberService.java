package UMC_10th_Spring_Study.service;

import UMC_10th_Spring_Study.domain.Member;
import UMC_10th_Spring_Study.repository.MemberRepository;
import UMC_10th_Spring_Study.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {
    
    private final MemberRepository memberRepository;

    public MemberService (MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        //회원가입
        //중복회원 금지
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }
}
