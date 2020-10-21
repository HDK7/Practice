package practice.mybatis.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice.mybatis.entity.Member;

@SpringBootTest
@Transactional
class memberServiceTest {
    //https://codevang.tistory.com/263
    @Autowired
    MemberService memberService;

    @Test
    public void 멤버가입() {
        memberService.createMember("hong", 27);
        Member member = memberService.findOneByName("hong");
        Assertions.assertEquals(member.getName(), "hong");
        Assertions.assertEquals(member.getAge(), 27);
    }
}