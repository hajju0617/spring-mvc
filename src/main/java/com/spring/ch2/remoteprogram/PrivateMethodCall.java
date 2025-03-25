package com.spring.ch2.remoteprogram;

import java.lang.reflect.Method;

public class PrivateMethodCall {
    public static void main(String[] args) throws Exception{
//        Hello hello = new Hello();
//        hello.main();       // private이라서 외부(다른 클래스에서) 호출 불가.


        // Reflection API로 다른 클래스 private 메서드 호출하기.
        Class helloClass = Class.forName("com.spring.ch2.remoteprogram.Hello");
        Hello hello = (Hello) helloClass.newInstance();
        Method main = helloClass.getDeclaredMethod("main");
        main.setAccessible(true);
        main.invoke(hello);
    }
}
