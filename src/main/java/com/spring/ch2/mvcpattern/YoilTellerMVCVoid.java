package com.spring.ch2.mvcpattern;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Calendar;

@Controller
public class YoilTellerMVCVoid {
    @RequestMapping("/getYoilMVCVoid") // http://localhost:8080/getYoilMVCVoid?year=2025&month=2&day=16
    public void main(int year, int month, int day, Model model) throws IOException {

        // 1. 유효성 검사.
//        if (!isValid(year, month, day)) {
//            return "yoilError";
//        }

        // 2. 요일 계산.
        char yoil = getYoil(year, month, day);

        // 3. 계산한 결과를 model에 저장.
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("yoil", yoil);

//        return "yoil";      // webapp/WEB-INF/view/yoil.jsp
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
