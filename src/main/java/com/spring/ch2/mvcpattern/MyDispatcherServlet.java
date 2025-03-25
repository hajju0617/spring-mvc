package com.spring.ch2.mvcpattern;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

// @WebServlet = @Controller + @RequestMapping
// 스프링에서는 클래스 앞에 @Controller 붙이고 메서드 앞에 @RequestMapping으로 연결해줬음.
// 서블릿에서는 메서드 단위로 매핑이 안 되고 클래스 단위로만 매핑이 됨. /myDispatcherServlet : 서블릿과 매핑할 주소.
@WebServlet("/myDispatcherServlet")  // http://localhost/myDispatcherServlet?year=2021&month=10&day=1 (year, month, day를 arg0, arg1, arg2로 수정해야 정상 작동됨.)
public class MyDispatcherServlet extends HttpServlet {
    // 서블릿은 'HttpServlet'를 상속 받아야하고 메서드 이름은 항상 'service'로 해야됨. 그리고 매개변수로 HttpServletRequest, HttpServletResponse 둘 다 꼭 적어줘야됨.
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map    map = request.getParameterMap();     // getParameterMap()을 통해 쿼리스트링을 map 형태로 (key-value) 가져옴.
        Model  model = null;
        String viewName = "";

        try {
            // YoilTellerMVC의 객체를 생성.
            Class clazz = Class.forName("com.spring.ch2.mvcpattern.YoilTellerMVC");
            Object obj = clazz.newInstance();

            // main메서드의 정보를 얻음.
            Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);

            // main메서드의 매개변수 목록(paramArr)을 읽어서 메서드 호출에 사용할 인자 목록(argArr)을 동적으로 만듦.
            Parameter[] paramArr = main.getParameters();
            Object[] argArr = new Object[main.getParameterCount()];

            // Object 배열 argArr를 채워주는 반복문.
            for(int i = 0; i < paramArr.length; i++) {
                String paramName = paramArr[i].getName();
                Class  paramType = paramArr[i].getType();
                Object value = map.get(paramName);

                // paramType중에 Model이 있으면, 생성 & 저장
                if (paramType==Model.class) {
                    argArr[i] = model = new BindingAwareModelMap();
                } else if (paramType==HttpServletRequest.class) {        // HttpServletRequest 하고
                    argArr[i] = request;
                } else if (paramType==HttpServletResponse.class) {       // HttpServletResponse 가 있으면 같이 넘겨줌.
                    argArr[i] = response;
                } else if (value != null) {  // map에 paramName이 있으면,
                    // value와 parameter의 타입을 비교해서, 다르면 변환해서 저장
                    String strValue = ((String[])value)[0];	        // getParameterMap()에서 꺼낸 value는 String 배열이므로 변환 필요함. 변환한 다음에 [0] 첫번째껄 꺼냄.
                                                                    // 'key'는 String이 맞지만 'value'는 String[]이 들어가 있음.   (현재 value는 Object 타입 (50번 라인 확인))
                                                                    // 왜냐하면 쿼리스트링으로 year=2025&year=2024..이런식으로 똑같은 key가 들어올 수 있음.
                    argArr[i] = convertTo(strValue, paramType);
                }
            }

            // Controller의 main()을 호출 - YoilTellerMVC.main(int year, int month, int day, Model model)
            viewName = (String)main.invoke(obj, argArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 텍스트 파일을 이용한 rendering
        render(model, viewName, response);
    }

    private Object convertTo(Object value, Class type) {
        if (type == null || value == null || type.isInstance(value)) // 타입이 같으면 그대로 반환
            return value;

        // 타입이 다르면, 변환해서 반환
        if (String.class.isInstance(value) && type == int.class) { // String -> int
            return Integer.valueOf((String)value);
        } else if (String.class.isInstance(value) && type == double.class) { // String -> double
            return Double.valueOf((String)value);
        }

        return value;
    }

    private String getResolvedViewName(String viewName) {
        return getServletContext().getRealPath("/WEB-INF/views") + "/" + viewName + ".jsp";
    }

    private void render(Model model, String viewName, HttpServletResponse response) throws IOException {
        // 매개변수로 HttpServletResponse를 추가해서 실제 응답은 콘솔이 아니라 브라우저에 출력.
        String result = "";

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        // 뷰의 내용을 한줄씩 읽어서 하나의 문자열로 만듦.
        Scanner sc = new Scanner(new File(getResolvedViewName(viewName)), "utf-8");

        while(sc.hasNextLine())
            result += sc.nextLine()+ System.lineSeparator();

        // model을 map으로 변환
        Map map = model.asMap();

        // key를 하나씩 읽어서 template의 ${key}를 value로 바꿈.
        Iterator it = map.keySet().iterator();

        while(it.hasNext()) {
            String key = (String)it.next();

            // replace()로 key를 value로 치환.
            result = result.replace("${"+key+"}", map.get(key) + "");
        }

        // 렌더링 결과를 출력.
        out.println(result);
    }
}
