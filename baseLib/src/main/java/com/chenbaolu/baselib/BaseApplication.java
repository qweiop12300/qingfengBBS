package com.chenbaolu.baselib;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

    private static SharedPreferences sharedPreferences;

    private volatile static String token;

    private volatile static Long userId;

    public static Retrofit getRetrofit(){
        if(retrofit==null){
            synchronized (BaseApplication.class){
                if(retrofit==null){
                    retrofit = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                            .client(OkHttpUtil.getUnsafeOkHttpClient())
                            .baseUrl("https://192.168.249.105:8081/")
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
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
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

    public static String getToken(){
        if (token==null){
            synchronized (Application.class){
                if (token==null){
                    if (sharedPreferences!=null){
                        token = sharedPreferences.getString("token","");
                    }
                }
            }
        }
        return token;
    }
    public static void setToken(String token2){
        token = token2;
        if(sharedPreferences!=null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("token",token);
            editor.apply();
        }
    }

    public static Long getUserId() {
        userId = sharedPreferences.getLong("userId",0);
        return userId;
    }

    public static void setUserId(Long userId2) {
        userId = userId2;
        if(sharedPreferences!=null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putLong("userId",userId);
            editor.apply();
        }
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static void setSharedPreferences(SharedPreferences sharedPreferences) {
        BaseApplication.sharedPreferences = sharedPreferences;
    }


}

