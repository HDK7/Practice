package jpabook.jpashop.Service;

import jpabook.jpashop.Repository.MemberRepository;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
//@AllArgsConstructor // 생성자를 만들어주고 @Autowired는 쓰지 않아도 자동 설정된다.
@RequiredArgsConstructor // final만 생성자를 만들어 준다.
// spring 라이브러리로 불러오는 것을 추천
public class MemberService {

    //final을 넣으면 컴파일 시점에 확인 가능하다.
    private final MemberRepository memberRepository;

    /*
    @Autowired // 생성자로 권장
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

    /*
     *회원 가입
     */
    @Transactional // 쓰기에는 read Only true를 넣으면 안된다. 그러면 데이터 변경이 안된다 디폴트는 false이고 클래스어노테이션보다 우선 적용
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){ // DB에 유니크로 제약조건을 걸어 한번더 마지노선을 만든다. 동시에 insert하는 멀티쓰레드 상황대비
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    //성능을 좀더 최적화 해줌, 더티 체킹을 안하고 리소스를 최소한으로 사용하고 등
    @Transactional(readOnly = true)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //@Transactional(readOnly = true) class에 적용 돼 있음
    public Member findOne(Long memberId){
        return memberRepository.findOne((memberId));
    }
}
