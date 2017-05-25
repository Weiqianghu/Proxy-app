package com.huweiqiang.hookams;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.huweiqiang.hookams.hook.HookHelper;

/**
 * Created by huweiqiang on 2017/5/25.
 */

public class App extends Application {
    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void attachBaseContext(Context base) {
        HookHelper.hookActivityManager();
        super.attachBaseContext(base);
        Log.d(TAG, "attachBaseContext: ");
    }
}
