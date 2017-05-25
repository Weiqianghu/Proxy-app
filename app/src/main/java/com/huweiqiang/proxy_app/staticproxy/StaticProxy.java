package com.huweiqiang.proxy_app.staticproxy;

import com.huweiqiang.proxy_app.RealSubject;
import com.huweiqiang.proxy_app.Subject;

/**
 * Created by huweiqiang on 2017/5/24.
 */

public class StaticProxy implements Subject {
    private Subject realSubject = new RealSubject();
    private long startMillis = 0;

    @Override
    public void request() {
        preRequest();
        realSubject.request();
        postRequest();
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
