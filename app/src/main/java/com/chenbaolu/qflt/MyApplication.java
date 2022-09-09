package com.chenbaolu.qflt;

import android.content.Context;
import android.content.SharedPreferences;

import com.chenbaolu.baselib.BaseApplication;

/**
 * 描述 :
 * 创建时间 : 2022/9/8 08:49
 * 作者 : 23128
 */
public class MyApplication extends BaseApplication {
    static SharedPreferences sharedPreferences;
    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

    }
    public static String getToken(){
        return sharedPreferences.getString("token","");
    }
    public static void setToken(String token){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token",token);
        editor.apply();
    }
}
