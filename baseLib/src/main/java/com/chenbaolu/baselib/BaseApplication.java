package com.chenbaolu.baselib;

import android.app.Application;
import android.content.Context;
/**
 * 描述 :
 * 创建时间 : 2022/9/7 16:06
 * 作者 : 23128
 */

public class BaseApplication extends Application {

    private static ActivityManager activityManager;

    private static BaseApplication application;

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //声明Activity管理
        activityManager = new ActivityManager();
        context = getApplicationContext();
        application = this;
    }


    public static BaseApplication getApplication() {
        return application;
    }

    public static Context getContext() {
        return context;
    }

    public static ActivityManager getActivityManager() {
        return activityManager;
    }
}

