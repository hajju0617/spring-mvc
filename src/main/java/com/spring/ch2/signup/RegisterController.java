package com.spring.ch2.signup;

import com.spring.ch2.validation.UserValidator;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class RegisterController {

    @InitBinder
    public void toDate(WebDataBinder webDataBinder) {
        ConversionService conversionService = webDataBinder.getConversionService();
//        System.out.println("conversionService = " + conversionService);

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
        webDataBinder.registerCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#"));
//        webDataBinder.setValidator(new UserValidator());    // UserValidator를 WebDataBinder의 로컬 Validator로 등록. (로컬 Validator : 해당 컨트롤러내에서만 동작.)
//        webDataBinder.addValidators(new UserValidator());
        List<Validator> validatorList = webDataBinder.getValidators();
        System.out.println("validatorList = " + validatorList);
    }


//    @GetMapping("/register/add")
    @RequestMapping(value = "/register/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String register() {        // servlet-context.xml 에서 view-controller로 대체했음.
        return "registerForm";      // webapp/WEB-INF/viewes/registerForm.jsp 반환.
    }

//    @RequestMapping(value = "/register/save", method = RequestMethod.POST)
    @PostMapping("/register/save")
    public String save(@Valid User user, BindingResult bindingResult, Model model) throws UnsupportedEncodingException {
        System.out.println("bindingResult = " + bindingResult);
        System.out.println("user = " + user);


        // 수동 검증 : Validator를 직접 생성해서 validate()를 직접 호출
//        UserValidator userValidator = new UserValidator();
//        userValidator.validate(user, bindingResult);        // BindingResult는 Errors 인터페이스의 자손.

        if (bindingResult.hasErrors()) {        // User객체를 검증한 결과 에러가 있을 경우 registerForm를 이용해서 에러를 보여줘야함.
            return "registerForm";
        }
        // 유효성 검사
//        if (!isValid(user)) {
//            String msg = URLEncoder.encode("id를 잘못 입력하셨습니다.", "utf-8");
//
//            // model을 이용하는 방법.
//            model.addAttribute("msg", msg);
//            return "forward:/register/add";
//
//            // GET방식으로 URL 뒤에 쿼리스트링으로 붙임.
////            return "redirect:/register/add?msg=" + msg; // 'URL 재작성(rewriting)' 이라고 함.
//        }

        // DB에 저장.

        return "registerInfo";
    }

    private boolean isValid(User user) {
        return true;
    }
}
