package com.spring.ch2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.FileNotFoundException;

@Controller
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)       // 응답 상태 코드 200 -> 500으로 바꿔줌.
    public String catcher(Exception ex, Model model) {
        System.out.println("catcher() in ExceptionController.");
        System.out.println("model = " + model);
//        model.addAttribute("ex", ex);
        return "error";
    }

    @ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
    public String catcher2(Exception ex, Model model) {
        model.addAttribute("ex", ex);
        return "error";
    }

    @RequestMapping("/ex")
    public String main(Model model) throws Exception {
        model.addAttribute("msg", "message from ExceptionController.main()");
        throw new Exception("예외가 발생했음.");
    }

    @RequestMapping("/ex2")
    public String main2() throws Exception {
        throw new FileNotFoundException("예외가 발생했음.");
    }
}
