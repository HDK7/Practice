package jpabook.jpashop.Repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext // 알아서 em을 주입해주는 어노테이션 스프링부트는 @Autowired로 대체 가능하고 이로인해 @RequiredARgsConstructor를 사용할수있게된다.
    //생성자 주입은 test시 매개변수를 넣어줘야해서 한번더 생각할수 있게 해준다.
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
