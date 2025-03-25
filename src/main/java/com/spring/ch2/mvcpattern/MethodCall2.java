package com.spring.ch2.mvcpattern;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

public class MethodCall2 {
    public static void main(String[] args) throws Exception{

        // YoilTellerMVC의 객체를 생성.
        Class clazz = Class.forName("com.spring.ch2.mvcpattern.YoilTellerMVC");
        Object obj = clazz.newInstance();

        // main() 메서드의 정보를 가져옴. ("main()" 메서드가 있고, 거기에 매개변수로 int값 3개와 Model을 받고 있음. (일치해야됨.))
        Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);

        // Model을 생성. (BindingAwareModelMap은 Spring에서 사용하는 거.)
        // Model을 찾아가보면 인터페이스, 즉 객체를 생성할 수 없음. 실제 Model의 구현체는 BindingAwareModelMap임.
        Model model = new BindingAwareModelMap();
        System.out.println("[before] model = " + model);

//        String viewName = obj.main(2025, 2, 18, model);          // Reflection API를 사용하지 않는다면 이렇게 호출. (Object 객체에 main()메서드 호출)
        // ↑ ↓ 위 아래는 같은 의미의 코드. main() 메서드 호출. (위쪽에 있는 Method main)
        String viewName = (String) main.invoke(obj, new Object[] { 2025, 2, 18, model });       // Reflection API로 호출. (invoke() 메서드 사용)
        System.out.println("viewName = " + viewName);

        // Model의 내용을 출력
        System.out.println("[after] model = " + model);

        // 텍스트 파일을 이용한 rendering
        render(model, viewName);
    } // main

    static void render(Model model, String viewName) throws IOException {
        String result = "";

        // 뷰의 내용을 한줄씩 읽어서 하나의 문자열로 만듦. (인코딩을 UTF8로 설정.)
        Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/" + viewName + ".jsp"), "utf-8");
        while(sc.hasNextLine())
            result += sc.nextLine() + System.lineSeparator();

        // model을 map으로 변환.
        Map map = model.asMap();

        // key를 하나씩 읽어서 template의 ${key}를 value로 바꿈.
        Iterator it = map.keySet().iterator();

        while (it.hasNext()) {
            String key = (String) it.next();

            // replace()로 key를 value로 치환.
            result = result.replace("${" + key + "}", "" + map.get(key));
        }

        // 렌더링 결과를 출력.
        System.out.println(result);
    }
}

