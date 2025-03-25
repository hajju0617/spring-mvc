package com.spring.ch2.mvcpattern;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

public class MethodCall3 {
    public static void main(String[] args) throws Exception{
        // 요청할 때 제공된 값. (httpServletRequest.getParameterMap();)
        Map map = new HashMap();
        map.put("arg0", "2025");    // 리플렉션 API가 매개변수명을 year, month, day로 못 하고 arg0, arg1, arg2로 떠서 IllegalArgumentException에러가 발생.
        map.put("arg1", "2");       // 그래서 임의로 실행이 되도록 arg0, arg1, arg2로 수정해놨음.
        map.put("arg2", "18");      // STS에는 store information about method parameters(usable via reflection) 설정이 있는데 IntelliJ에서는 아무리 찾아봐도 안됨,,

        Model model = null;
        Class clazz = Class.forName("com.spring.ch2.mvcpattern.YoilTellerMVC");
        Object obj  = clazz.newInstance();

        // YoilTellerMVC.main(int year, int month, int day, Model model)
        Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);

        Parameter[] paramArr = main.getParameters();                // main() 메서드의 매개변수 목록을 가져옴.
        Object[] argArr = new Object[main.getParameterCount()];     // 매개변수 개수와 같은 길이의 Object 배열을 생성.
                                                                    // MethodCall2와는 다르게 하드코딩 하지않고 동적으로 구성. (map의 값들을 이용해서)
                                                                    // 동적으로 구성 -> 요청할 때 넘어온 값들을 가지고 객체 배열을 생성한다는 것.

        // argArr 객체 배열을 채우는 반복문.
        for(int i = 0; i < paramArr.length; i++) {
            String paramName = paramArr[i].getName();       // 매개변수 이름.
            Class  paramType = paramArr[i].getType();       // 매개변수 타입.
            Object value = map.get(paramName);              // map에서 못찾으면 value는 null

            // paramType중에 Model이 있으면, 생성 & 저장
            if(paramType == Model.class) {
                argArr[i] = model = new BindingAwareModelMap();
            } else if(value != null) {  // map에 paramName이 있으면,
                // value와 parameter의 타입을 비교해서, 다르면 변환해서 저장
                argArr[i] = convertTo(value, paramType);
            }
        }
        System.out.println("paramArr = " + Arrays.toString(paramArr));
        System.out.println("argArr = " + Arrays.toString(argArr));


        // Controller의 main()을 호출 - YoilTellerMVC.main(int year, int month, int day, Model model)
        String viewName = (String)main.invoke(obj, argArr);
        System.out.println("viewName = " + viewName);

        // Model의 내용을 출력
        System.out.println("[after] model = " + model);

        // 텍스트 파일을 이용한 rendering
        render(model, viewName);
    }

    private static Object convertTo(Object value, Class type) {
        if(type==null || value==null || type.isInstance(value)) {   // 타입이 같으면 그대로 반환
            return value;
        }


        // 타입이 다르면, 변환해서 반환
        if(String.class.isInstance(value) && type == int.class) {               // String -> int 변환
            return Integer.valueOf((String)value);
        } else if(String.class.isInstance(value) && type == double.class) {     // String -> double 변환
            return Double.valueOf((String)value);
        }

        return value;
    }

    private static void render(Model model, String viewName) throws IOException {
        String result = "";

        // 뷰의 내용을 한줄씩 읽어서 하나의 문자열로 만듦.
        Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/" + viewName + ".jsp"), "utf-8");
        while(sc.hasNextLine())
            result += sc.nextLine() + System.lineSeparator();

        // model을 map으로 변환
        Map map = model.asMap();

        // key를 하나씩 읽어서 template의 ${key}를 value로 바꿈.
        Iterator it = map.keySet().iterator();

        while(it.hasNext()) {
            String key = (String) it.next();

            // replace()로 key를 value로 치환.
            result = result.replace("${" + key + "}", "" + map.get(key));
        }

        // 렌더링 결과를 출력.
        System.out.println(result);
    }
}
