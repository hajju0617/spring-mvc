package com.spring.ch2.session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
    @GetMapping("/list")
    public String list(HttpServletRequest httpServletRequest) {
        if (!loginCheck(httpServletRequest)) {
            return "redirect:/login/login?toURL=" + httpServletRequest.getRequestURL();     // 로그인이 안되어 있으면.
        }
        return "boardList";     // 로그인이 되어 있으면.
    }

    private boolean loginCheck(HttpServletRequest httpServletRequest) {
        // 1. 세션을 얻어서
        HttpSession httpSession = httpServletRequest.getSession();
        // 2. id가 있는지 확인.
        return httpSession.getAttribute("id") != null;      // null이 아니면 로그인 상태이므로 true.
    }
}
