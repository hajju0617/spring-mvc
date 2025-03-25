package com.spring.ch2.servletandjsp;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

// urlPatterns : 필터를 적용할 요청의 패턴 지정 - 모든 요청에 필터를 적용.
@WebFilter(urlPatterns="/*")    // 필터를 등록하는 에노테이션. (PerformanceFilter를 필터로 등록하고, 해당 필터를 적용할 대상을 지정.)
public class PerformanceFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 초기화 작업
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 1. 전처리 작업
        long startTime = System.currentTimeMillis();

        // 2. 서블릿 또는 다음 필터를 호출
        chain.doFilter(request, response);

        // 3. 후처리 작업
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String referer = httpServletRequest.getHeader("referer");                 // 어디서 요청을 했는지 알 수 있음.
        String method = httpServletRequest.getMethod();                             // 요청한 메서드 타입.
        System.out.println("[" + referer + "] -> " + method + " [" + httpServletRequest.getRequestURI()+"] (URI 정보)");  // 어디서 어떤 메서드를 사용해서 어디로 요청을 보냈는지 알 수 있음.
        System.out.println("[" + referer + "] -> " + method + " [" + httpServletRequest.getRequestURL()+"] (URL 정보)");  // 어디서 어떤 메서드를 사용해서 어디로 요청을 보냈는지 알 수 있음.
        System.out.println(" 소요시간="+(System.currentTimeMillis()-startTime)+"ms");
    }

    @Override
    public void destroy() {
        // 정리 작업
    }

}
