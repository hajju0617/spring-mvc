package com.spring.ch2.validation;

import com.spring.ch2.signup.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);            // 검증하려는 객체의 타입이 User 타입 인지 확인. (equals() 대신 isAssignableFrom() 사용 가능)
//        return User.class.isAssignableFrom(clazz);  // 검증하려는 객체의 타입이 User 또는 그 자손인지 확인.
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("UserValidator.validate() is called");

        User user = (User)target;

        String id = user.getId();

        //		if(id==null || "".equals(id.trim())) {          // id가 null이거나 ""(빈문자열)일 경우 errors에 에러를 저장.
        //			errors.rejectValue("id", "required");       // "id"라는 필드에 "required"라는 에러코드를 저장.
        //		}

        // 비어있거나 공백일 경우 errors객체에 필드이름을 id, 에러코드를 required로 저장하라는 의미.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",  "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");

        if(id==null || id.length() <  5 || id.length() > 12) {
            errors.rejectValue("id", "invalidLength");
        }
    }
}
