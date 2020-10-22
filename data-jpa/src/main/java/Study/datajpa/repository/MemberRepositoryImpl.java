package Study.datajpa.repository;

import Study.datajpa.entity.Member;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    //클래스 이름은 MemberRepository + Impl 이 필수다. 그래야 스프링이 인식
    //XML로 설정하거나 javaconfig 설정으로 클래스 이름 변경이 가능하지만 번거롭다.

    private final EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m")
                .getResultList();
    }
}
