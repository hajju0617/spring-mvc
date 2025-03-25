package com.spring.ch2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.FileNotFoundException;

@Controller
public class ExceptionController2 {
    @RequestMapping("/ex3")
    public String main3() throws Exception {
        throw new MyException("예외가 발생했음.");
    }

    @RequestMapping("/ex4")
    public String main4() throws Exception {
        throw new FileNotFoundException("예외가 발생했음.");
    }
}

//@ResponseStatus(HttpStatus.BAD_REQUEST)         // 응답 상태 코드를 500 -> 400으로 바꿈
class MyException extends RuntimeException {
    MyException(String msg) {
        super(msg);
    }
    MyException() {
        this("");
    }
}
