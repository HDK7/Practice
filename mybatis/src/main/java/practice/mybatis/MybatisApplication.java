package practice.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import practice.mybatis.service.MemberService;

@SpringBootApplication
public class MybatisApplication {

	//https://enterkey.tistory.com/320

	@Autowired
	private static MemberService memberService;

	public static void main(String[] args) {
		SpringApplication.run(MybatisApplication.class, args);
	}

}
