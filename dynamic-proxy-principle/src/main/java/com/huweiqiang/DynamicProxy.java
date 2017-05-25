package com.huweiqiang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by huweiqiang on 2017/5/24.
 */

public class DynamicProxy implements InvocationHandler {
    private Object subject = new RealSubject();
    private long startMillis;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        preRequest();
        Object result = method.invoke(subject, args);
        postRequest();
        return result;
    }

    private void preRequest() {
        startMillis = System.currentTimeMillis();
        System.out.println("request start:" + startMillis);
    }

    private void postRequest() {
        long endMillis = System.currentTimeMillis();
        System.out.println("request end:" + endMillis + ",duration:" + (endMillis - startMillis));
    }
}
