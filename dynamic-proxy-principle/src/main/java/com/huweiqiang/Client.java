package com.huweiqiang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by huweiqiang on 2017/5/24.
 */

public class Client {
    public static void main(String[] args) {
        dynamicProxy();
    }

    private static void dynamicProxy() {
        InvocationHandler handler = new DynamicProxy();
        Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(), RealSubject.class.getInterfaces(), handler);
        subject.request();

        ProxyUtils.saveProxyClass(subject.getClass().getSimpleName() + ".class", subject.getClass().getSimpleName(), subject.getClass().getInterfaces());
    }
}
