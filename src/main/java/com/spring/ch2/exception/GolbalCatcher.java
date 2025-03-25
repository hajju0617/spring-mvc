package com.spring.ch2.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

//@ControllerAdvice   // 모든 패키지에서 발생한 예외처리. (전역 예외 처리 클래스 등록)
@ControllerAdvice("com.spring.ch3")   // 지정된 패키지에서 발생한 예외만 처리.
public class GolbalCatcher {
//    @ExceptionHandler(Exception.class)
//    public String catcher(Exception ex, Model model) {
//        model.addAttribute("ex", ex);
//        return "error";
//    }

    @ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
    public String catcher2(Exception ex, Model model) {
        model.addAttribute("ex", ex);
        return "error";
    }
}
