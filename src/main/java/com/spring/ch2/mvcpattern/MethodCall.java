package com.spring.ch2.mvcpattern;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


class ModelController {                 // 컨트롤러 역할.
    public String main(HashMap map) {   // 매개변수로 HashMap 선언.

        // 처리 결과를 map에 저장.
        map.put("id", "asdf");
        map.put("pwd", "1111");

        return "txtView1";              // 뷰 이름을 반환함.
    }
}
public class MethodCall {
    public static void main(String[] args) throws Exception{
        HashMap map = new HashMap();                // HashMap 생성.
        System.out.println("before:"+map);

        ModelController mc = new ModelController(); // ModelController 생성.
        String viewName = mc.main(map);     // ModelController의 main메서드를 map을 인자로 해서 호출.

        System.out.println("after :"+map);

        render(map, viewName);      // render 메서드 호출(데이터(map), 뷰 이름("txtView2.txt"))
    }

    static void render(HashMap map, String viewName) throws IOException {
        String result = "";

        // 뷰의 내용을 한줄씩 읽어서 하나의 문자열로 만듦.
        Scanner sc = new Scanner(new File(viewName+".txt"));

        while(sc.hasNextLine())
            result += sc.nextLine()+ System.lineSeparator();

        // map에 담긴 key를 하나씩 읽어서 template의 ${key}를 value로 바꿈.
        Iterator it = map.keySet().iterator();

        while(it.hasNext()) {
            String key = (String)it.next();

            // replace()로 key를 value로 치환.
            result = result.replace("${"+key+"}", (String)map.get(key));
        }

        // 렌더링 결과를 출력.
        System.out.println(result);
    }
}


