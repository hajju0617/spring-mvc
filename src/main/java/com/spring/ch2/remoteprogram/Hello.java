package com.spring.ch2.remoteprogram;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 1. 원격 호출 가능한 프로그램으로 등록.
@Controller
public class Hello {
    int iv = 10;            // 인스턴스 변수.
    static int cv = 20;     // static 변수.

    // 2. URL과 메서드를 연결.
    @RequestMapping("/hello")
    private void main() {                    // 인스턴스 메서드 : iv, cv 둘 다 사용 가능.
        System.out.println("Hello");
        System.out.println(cv);
        System.out.println(iv);
    }

    public static void main2() {            // static 메서드 : cv만 사용 가능.
        System.out.println("Hello");
        System.out.println(cv);
//        System.out.println(iv);           // 에러.
    }
}
