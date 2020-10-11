package com.example.demo.service;

import com.example.demo.Repository.MemberRepository;
import com.example.demo.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원 가입
     */

    public Long join(Member member){
        //같은 이름이 있는 중복 회원X
            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
            return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        //result.orElseGet() 이함수도 많이씀 값이 없으면 ~ 실행
                //  ~가 있다면, 즉 null이 아니라면 /// m은 Optional<T>에서 T이다. 즉 Member 데이터 타입
            .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    
    /*
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
