//package jpabook.jpashop;
//
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//@Repository
//public class MemberRepository {//DAO 같은거임
//
//    @PersistenceContext //스프링부트가 Entity Manager를 주입함
//    private EntityManager em;
//
//    public Long save(Member member){
//        em.persist(member);
//        return member.getId();
//    }
//
//    public Member find(Long id){
//        return em.find(Member.class, id);
//    }
//
//}
