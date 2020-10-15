package Study.datajpa.repository;

import Study.datajpa.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository 생략가능 인터페이스를 상속받은 것만으로도 인식
public interface TeamRepository extends JpaRepository<Team, Long> {// <타입, id>
}
