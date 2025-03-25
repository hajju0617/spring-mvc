package com.spring.ch2.cookie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        // 세션 종료.
        httpSession.invalidate();
        // 홈으로 이동.
        return "redirect:/";
    }
    @PostMapping("/login")
    public String login(String id, String pwd, String toURL, boolean rememberId, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws UnsupportedEncodingException {
        System.out.println("id = " + id + ", pwd = " + pwd + ", rememberId = " + rememberId);
        // 1. id, pwd를 확인.
        if (!loginCheck(id, pwd)) {
            // 2-1. 일치하지 않으면 loginForm으로 이동.
            String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다", "UTF-8");
            return "redirect:/login/login?msg=" + msg;
        }

        // 2-2. id, pwd가 일치하면
            // 세션 객체 얻어오기.
        HttpSession httpSession = httpServletRequest.getSession();
            // 세션 객체에 id 저장.
        httpSession.setAttribute("id", id);
         // 1. 쿠키를 생성.
        Cookie cookie = new Cookie("id", id);
        if (!rememberId) {
            cookie.setMaxAge(0);
        }
         // 2. 응답에 저장.
        httpServletResponse.addCookie(cookie);
         // 3. 홈으로 이동. (or 게시판으로 이동. 로그인 안 한 상태에서 게시판 접근 시 로그인 페이지로 리다이렉트 되는데 여기서 로그인 할 시 기존 요청대로 게시판으로 이동하도록)
        toURL = toURL == null || toURL.equals("") ? "/" : toURL;
        return "redirect:" + toURL;
    }

    private boolean loginCheck(String id, String pwd) {
        return "asdf".equals(id) && "1234".equals(pwd);
    }
}
