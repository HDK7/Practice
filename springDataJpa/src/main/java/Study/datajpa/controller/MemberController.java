package Study.datajpa.controller;

import Study.datajpa.dto.MemberDto;
import Study.datajpa.entity.Member;
import Study.datajpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id){
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    @GetMapping("/members2/{id}")
    public String findMember(@PathVariable("id") Member member){
        return member.getUsername();
    }

    @GetMapping("/members")
    public Page<MemberDto> list(@PageableDefault(size = 5) Pageable pageable) { // ?page=1&size=3&sort=id,desc&sort=username,desc
        //global 설정은 yml에 but 어노테이션 우선적용
        //Qualifier도 적용가능 2개의 페이지 사용 참고
        Page<Member> page = memberRepository.findAll(pageable);
        return page.map(MemberDto::new);
    }


    //@PostConstruct
    public void init(){
        for(int i = 0; i < 100; i++){
            memberRepository.save(new Member("user" + i, i));
        }
    }
}
