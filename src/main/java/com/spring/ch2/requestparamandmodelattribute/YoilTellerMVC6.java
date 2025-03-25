package com.spring.ch2.requestparamandmodelattribute;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

@Controller
public class YoilTellerMVC6 {
    @ExceptionHandler(Exception.class)          // Exception이 발생하면 호출.
    public String catchException(Exception ex, BindingResult bindingResult) {
        System.out.println("bindingResult = " + bindingResult);
        FieldError fieldError = bindingResult.getFieldError();

        System.out.println("fieldError.getCode() = " + fieldError.getCode());
        System.out.println("fieldError.getField() = " + fieldError.getField());
        System.out.println("fieldError.getDefaultMessage() = " + fieldError.getDefaultMessage());

        ex.printStackTrace();
        return "yoilError";
    }
    @RequestMapping("/getYoilMVC6") // http://localhost:8080/getYoilMVC6?year=2025&month=2&day=16
    public String main(MyDate myDate, BindingResult bindingResult) {
        System.out.println("bindingResult = " + bindingResult);

        // 1. 유효성 검사.
        if (!isValid(myDate)) {
            return "yoilError";     // 유효하지 않으면, /WEB-INF/views/yoilError.jsp로 이동
        }

        return "yoil";      // webapp/WEB-INF/view/yoil.jsp (작업 결과를 보여줄 View의 이름을 반환)
    }

    private boolean isValid(MyDate myDate) {
        return isValid(myDate.getYear(), myDate.getMonth(), myDate.getDay());
    }

    // @ModelAttribute를 통해 메서드의 처리결과가 Model에 저장됨. key=yoil.
    private @ModelAttribute("yoil") char getYoil(MyDate myDate) {
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
