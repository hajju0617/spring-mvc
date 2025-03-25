package com.spring.ch2.servletandjsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")   // URL 매핑.
public class HelloServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        // 서블릿이 초기화될때 자동 호출되는 메서드.
        // 서블릿의 초기화 작업을 담당.
        // 처음에 한 번만 호출됨.
        System.out.println("[HelloServlet] init() is called");
    }

    @Override
    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        // 요청이 올 때마다 서비스가 계속 반복적으로 호출되면서 처리됨.
        // 입력
        // 처리
        // 출력
        System.out.println("[HelloServlet] service() is called");
    }
    @Override
    public void destroy() {
        // 서블릿이 메모리에서 제거될 때(새로 개신돼서 다시 리로딩 되거나 웹 애플리케이션이 종료될 때) 서블릿 컨터이너에 의해서 자동 호출되는 메서드.
        // 서블릿과 관련된 뒷마무리 작업을 하기 위함.
        System.out.println("[HelloServlet] destroy() is called");
    }
}
