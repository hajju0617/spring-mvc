package com.spring.ch2.httpreqres;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

// 년월일을 입력하면 요일을 알려주는 프로그램.
@Controller
public class YoilTeller3 {
    @RequestMapping("/getYoil3")
    public void main(HttpServletRequest httpServletRequest
                   , HttpServletResponse httpServletResponse) throws IOException {
        // 1. 입력.
        String year = httpServletRequest.getParameter("year");
        String month = httpServletRequest.getParameter("month");
        String day = httpServletRequest.getParameter("day");

        // String -> int 형변환.
        int yyyy = Integer.parseInt(year);
        int mm = Integer.parseInt(month);
        int dd = Integer.parseInt(day);

        // 2. 작업.
        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar = " + calendar);
        calendar.set(yyyy, mm - 1, dd);

        // DAY_OF_WEEK => 요일 (숫자로 나옴. 1:일요일, 2:월요일,...)
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        char yoil = " 일월화수목금토".charAt(dayOfWeek);

        // 3. 출력.
        httpServletResponse.setContentType("text/html");            // 출력할 내용의 타입.
        httpServletResponse.setCharacterEncoding("utf-8");          // text의 인코딩 정보.
        PrintWriter printWriter = httpServletResponse.getWriter();    // httpServletResponse 객체에서 브라우저로의 출력 스트림을 얻음.
        printWriter.println(year + "년 " + month + "월 " + day + "일은 ");
        printWriter.println(yoil + "요일입니다.");
    }
}
