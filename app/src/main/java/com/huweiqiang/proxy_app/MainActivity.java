package com.huweiqiang.proxy_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.huweiqiang.proxy_app.dynamicproxy.DynamicProxy;
import com.huweiqiang.proxy_app.staticproxy.StaticProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void staticProxy(View view) {
        Subject subject = new StaticProxy();
        subject.request();
    }

    public void dynamicProxy(View view) {
        InvocationHandler handler = new DynamicProxy();
        Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(), RealSubject.class.getInterfaces(), handler);
        subject.request();
    }
}
