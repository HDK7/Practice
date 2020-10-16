package Study.datajpa.repository;

import Study.datajpa.dto.MemberDto;
import Study.datajpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findHelloBy();

    List<Member> findByUsername(String username);

    List<Member> findTop3HelloBy();

    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);
    //장점 이름을 길게 설정 안해도 된다.
    //메소드로 만드는것(간단한것 만들 때)과 이것 둘다 많이쓴다.
    //오타가 났을시 컴파일에서 잡아준다.

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new Study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();
    //Querydsl을 이용해 더 간편하게 만들 수 있다.

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);
    //in을 List로 받는것 예시

    List<Member> findListByUsername(String username);
    Member findMemberByUsername(String username);
    Optional<Member> findOptionalByUsername(String username);
    // 유연한 반환 타입
    // 주의 : 조회 된 것이 없어도 null을 반환하지 않고 size가 0 인 List 등을 반환하므로 ( XX == null) 이런 조건문은 못쓴다.
    // 단건인 경우에는 null이 반환된다.
    // JPA 는 반환 결과가 없으면 NoresultException이 발생하지만 Spring data JPA는 null 혹은 size가 0인 데이터배열타입으로 반환한다.
    // 결과가 있을 수도 있고 없을 수도 있으면 Optional로 받고 .orelse 등으로 처리한다.
    // Optional<Member> 단건 조회에서 2개이상이 조회되면 Exception이 발생한다. 는 스프링 Exception 타입으로 변환된다.

    @Query(value = "select m from Member m left join m.team t",
            countQuery = "select count(m) from Member m")
    Page<Member> findByAge(int age, Pageable pageable); //Peageable을 페이지 라고 보면 된다.
    // Count 쿼리 table만 따로 작성해준 모싑이다. 성능 향상을 위해

    @Modifying(clearAutomatically = true) // executeUpdate로 실행되어서 반환을 int로 한다. 없으면 에러발생
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);
    //문제 : DB에 바로 적용되기 때문에 DB는 41살 영속성 컨텍스트에서는 40살로 되어있는 문제가 생긴다. 그래서 영속성컨텍스트를 리로드 해야함.
    //em.flush() 와 em.clear() 가 뒤따라 와야함 clearAutomatically = true가 이역할을 대신 해줌
    //DB 업데이트만 해줄거면 상관없는데 업데이트 후 조회 해줄때 문제가 되는 것.

}
