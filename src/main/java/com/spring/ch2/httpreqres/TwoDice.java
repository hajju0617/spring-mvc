package com.spring.ch2.httpreqres;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class TwoDice {
    // 호출이 되면 주사위 2개를 보여줌.
    @RequestMapping("/rollDice")
    public void main(HttpServletResponse httpServletResponse) throws IOException {
        int index1 = (int) (Math.random() * 6) + 1;
        int index2 = (int) (Math.random() * 6) + 1;

        httpServletResponse.setContentType("text/html");
        httpServletResponse.setCharacterEncoding("utf-8");
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.println("<html>");
        printWriter.println("<head>");
        printWriter.println("</head>");
        printWriter.println("<body>");
        printWriter.println("<img src='/resources/img/dice" + index1 + ".jpg'>");
        printWriter.println("<img src='/resources/img/dice" + index2 + ".jpg'>");
        printWriter.println("<body>");
        printWriter.println("</html>");
    }
}
