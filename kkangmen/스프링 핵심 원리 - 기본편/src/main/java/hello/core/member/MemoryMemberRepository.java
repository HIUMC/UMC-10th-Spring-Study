package hello.core.member;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static final HashMap<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public void save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
