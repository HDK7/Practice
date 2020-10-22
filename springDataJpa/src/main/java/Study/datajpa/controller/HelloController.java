package Study.datajpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloController {
//    @RequestMapping("/hello")
//    public String hello() {
//        return "hello";
//    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}