package jpabook.jpashop.Service;

import jpabook.jpashop.Repository.MemberRepository;
import jpabook.jpashop.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest // 없으면 Autowired 전부 실패. spring과 연동이 안됨
@Transactional // 끝나면 rollbaack test가 아니고 레포지터리 같은 곳이면 commit
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;


    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(saveId)); // pk가 같으면 같은
    }

    @Test(expected = IllegalStateException.class) // 예외 발생을 성공으로 인식
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        //when
        memberService.join(member1);
        memberService.join(member2);
        /*
        try {
            memberService.join(member2); // 예외 발생
        } catch(IllegalStateException e){
            return;
        }
        */
         
        //then
        fail("예외가 발생해야 한다."); // 여기까지 도착하면 fail를 떨군다
    }

}