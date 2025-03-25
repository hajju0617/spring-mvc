package com.spring.ch2.textandbinary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
public class RequestHeader {
    // 요청한 헤더 정보를 출력.
    @RequestMapping("/requestHeader")
    public void main(HttpServletRequest httpServletRequest) {
        Enumeration<String> e = httpServletRequest.getHeaderNames();

        while (e.hasMoreElements()) {
            String name = e.nextElement();
            System.out.println(name +  " : " + httpServletRequest.getHeader(name));
        }
    }
}
