package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){ //데이터를 심어서 view에 넘기는 model
        model.addAttribute("data","hello!!!");
        return "hello"; //return은 화면 이름 (.html생략)
    }

}
