package com.spring.ch2.textandbinary;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestMessage {
    @RequestMapping("/requestMessage")
    public void main(HttpServletRequest request) throws Exception {

        // 1. request line (헤더 바로 위에 있는 줄) (Ex. GET /requestMessage HTTP/1.1)
        String requestLine = request.getMethod();           // GET 또는 POST
        requestLine += " " + request.getRequestURI();       // /requestMessage

        String queryString = request.getQueryString();      // year=2025&month=2&day=15
        requestLine += queryString == null ? "" : "?" + queryString;
        requestLine += " " + request.getProtocol();         // HTTP/1.1
        System.out.println(requestLine);


        // 2. request headers
        Enumeration<String> e = request.getHeaderNames();

        while (e.hasMoreElements()) {
            String name = e.nextElement();
            System.out.println(name + " : " + request.getHeader(name));
        }

        // 3. request body - POST일 때만 해당, GET은 body가 없음(CONTENT_LENGTH=0)
        final int CONTENT_LENGTH = request.getContentLength();
//		System.out.println("content length="+CONTENT_LENGTH);

        if(CONTENT_LENGTH > 0) {        // body가 존재할 경우.
            byte[] content = new byte[CONTENT_LENGTH];

            InputStream in = request.getInputStream();
            in.read(content, 0, CONTENT_LENGTH);

            System.out.println(); // empty line
            System.out.println(new String(content, "utf-8")); // year=2025&month=2&day=15
        }
    }
}
