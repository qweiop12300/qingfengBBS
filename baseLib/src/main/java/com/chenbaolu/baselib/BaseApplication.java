package com.chenbaolu.baselib;

import android.app.Application;
import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述 :
 * 创建时间 : 2022/9/7 16:06
 * 作者 : 23128
 */

public class BaseApplication extends Application {

    private static ActivityManager activityManager;

    private static BaseApplication application;

    private static Context context;

    private static volatile Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if(retrofit==null){
            synchronized (BaseApplication.class){
                if(retrofit==null){
                    retrofit = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                            .client(OkHttpUtil.getUnsafeOkHttpClient())
                            .baseUrl("https://192.168.228.105:8081/")
                            .build();
                }
            }
        }
        return retrofit;
    }



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

