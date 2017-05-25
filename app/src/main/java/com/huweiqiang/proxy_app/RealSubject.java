package com.huweiqiang.proxy_app;

import java.util.Random;

/**
 * Created by huweiqiang on 2017/5/24.
 */

public class RealSubject implements Subject {
    @Override
    public void request() {
        try {
            Thread.sleep((new Random().nextInt(800)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("RealSubject request");
    }
}
