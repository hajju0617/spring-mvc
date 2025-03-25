package com.spring.ch2.mvcpattern;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;

public class MethodInfo {
    public static void main(String[] args) throws Exception {
        // YoilTeller4 클래스의 객체를 생성.
        Class cla = Class.forName("com.spring.ch2.mvcpattern.YoilTellerMVC");
        Object obj = cla.newInstance();

        // 2. 모든 메서드 정보를 가져와서 배열에 저장.
        Method[] methodArr = cla.getDeclaredMethods();
        for (Method m : methodArr) {
            // 메서드 이름.
            String name = m.getName();
            // 메서드 매개변수 목록.
            Parameter[] paramArr = m.getParameters();
            // 메서드 반환 타입.
            Class returnType = m.getReturnType();

            // StringJoiner(", ", "(", ")") => , : 구분자       ( : 접두사     ) : 접미사
            // Ex. (.. , .., .., )
            StringJoiner paramList = new StringJoiner(", ", "(", ")");
            for (Parameter param : paramArr) {
                String paramName = param.getName();
                Class paramType = param.getType();

                paramList.add(paramType.getName() + " " + paramName);
            }
            System.out.printf("%s %s%s%n", returnType.getName(), name, paramList);
        }
    }
}
