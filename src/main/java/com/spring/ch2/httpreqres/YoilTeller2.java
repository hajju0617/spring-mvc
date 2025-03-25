package com.spring.ch2.httpreqres;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

// 년월일을 입력하면 요일을 알려주는 프로그램.
@Controller
public class YoilTeller2 {
    @RequestMapping("/getYoil2")
    public void main(HttpServletRequest httpServletRequest) {
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
        System.out.println(year + "년 " + month + "월 " + day + "일은 ");
        System.out.println(yoil + "요일입니다.");
    }
}
