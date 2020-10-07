package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")//localhost:8081/hello 는 여기로 와라
    public String hello(Model model){ // model은 공유함
        model.addAttribute("data", "hello!!");
        return "hello"; // 이름이 templates/hello.html으로 가라
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // Hello 클래스의 field 변수명이 key 값이 value 로 전달
    }
    static class Hello{
        private String name;        

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
