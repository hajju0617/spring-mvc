package com.spring.ch2.requestparamandmodelattribute;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;

@Controller
public class YoilTellerMVC4 {
//    @ExceptionHandler(Exception.class)          // Exception이 발생하면 호출.
//    public String catchException(Exception ex) {
//        ex.printStackTrace();
//        return "yoilError";
//    }
    @RequestMapping("/getYoilMVC4") // http://localhost:8080/getYoilMVC4?year=2025&month=2&day=16
    public String main(MyDate myDate, Model model) {

        // 1. 유효성 검사.
        if (!isValid(myDate)) {
            return "yoilError";     // 유효하지 않으면, /WEB-INF/views/yoilError.jsp로 이동
        }

        // 2. 요일 계산.
        char yoil = getYoil(myDate);

        // 3. 계산한 결과를 model에 저장.
        model.addAttribute("myDate", myDate);   // model에 저장할 때 key는 myDate, 그리고 거기 안에 myDate 객체가 들어있음.
        model.addAttribute("yoil", yoil);

        return "yoil";      // webapp/WEB-INF/view/yoil.jsp (작업 결과를 보여줄 View의 이름을 반환)
    }

    private boolean isValid(MyDate myDate) {
        return isValid(myDate.getYear(), myDate.getMonth(), myDate.getDay());
    }

    private char getYoil(MyDate myDate) {
        return getYoil(myDate.getYear(), myDate.getMonth(), myDate.getDay());
    }

    private boolean isValid(int year, int month, int day) {
        return true;
    }

    private static char getYoil(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return " 일월화수목금토".charAt(dayOfWeek);   // 일요일:1, 월요일:2, ...
    }
}
