package hello.hello_spring.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Hello!");
        return "hello";
    }

    /// mvc 방식
    /// 템플릿 엔진을 mvc로 쪼개서
    /// View를 템플릿 엔진으로 수정하여
    /// 렌더링된 html을 client로 전달
    @GetMapping("hello-mvc") // mvc 방식의 컨트롤러
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    ///  @ResponseBody 어노테이션이 붙어있는 경우
    /// ViewResolver를 거치지 않고 HttpMessageConverter가 동작함
    /// 1. 반환할 값이 String인 경우 - StringConverter가 동작
    /// 2. 반환할 값이 객체인 경우 - JSONConverter가 동작
    /// String 스타일로 반환 or JSON 스타일로 반환
    @GetMapping("hello-string") // api
    @ResponseBody // Response Body에 return에 해당하는 내용을 바로 내리겠다는 뜻
    public String helloString(@RequestParam("name") String name) {
        return  "hello " + name;
    }

    @GetMapping("hello-api") // JSON 형식으로 ResponseBody에 넘기기
    @ResponseBody // 객체를 반환하면 JSON 형식으로 데이터를 만들어서 반환하는게 기본
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    // getter/setter
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            name = name;
        }
    }


}
