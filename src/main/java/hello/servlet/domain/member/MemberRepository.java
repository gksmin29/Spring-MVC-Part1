package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    // MemberRepository에서 아래의 필드는 하나를 공유하여 사용하도록 하기 위해 static으로 선언
    // (사실 싱글톤이기 때문에 해당 필드는 static이 없어도 된다.)
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 해당 클래스는 싱글톤으로 관리할 것임
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    // 싱글톤을 위해 생성자는 private으로 선언
    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
