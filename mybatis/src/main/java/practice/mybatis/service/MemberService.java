package practice.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.mybatis.entity.Member;
import practice.mybatis.mybatisMapper.MemberMapper;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    @Autowired
    public MemberService( MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public void createMember(String name, int age){
        memberMapper.insertMember(name, age);
    }

    public Member findOneByName(String name){
        return memberMapper.findByName(name);
    }
}
