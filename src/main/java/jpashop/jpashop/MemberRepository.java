package jpashop.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


//Repository는 엔티티 등을 찾아주는
//DAO와 비슷
@Repository
public class MemberRepository {
    @PersistenceContext
    EntityManager em;
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }
    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
