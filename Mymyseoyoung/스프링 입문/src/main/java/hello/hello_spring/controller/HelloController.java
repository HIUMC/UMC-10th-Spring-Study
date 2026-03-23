package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    //model = controller -> view로 데이터를 전달해주는 역할
    public String hello(Model model)
    {
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model)
    {
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    //ResponseBody : 응답 바디부에 이 데이터를 넣어줄 것이다 ! (JSON)
    @ResponseBody
    public String helloString(@RequestParam("name") String name)
    {
        return "hello "+name;//hello spring ( 이 문자 그대로 내려감 .)
    }

    @GetMapping("hello-api")
    @ResponseBody

    public Hello helloApi(@RequestParam("name")String name)
    {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    //객체 만들기
    static class Hello
    {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
