package Study.datajpa.repository;

import Study.datajpa.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final EntityManager em;

    List<Member> findAllMembers(){
        return em.createQuery("select m from Member m")
                .getResultList(); // 복잡한 querydsl이라고 생각
    }

}
