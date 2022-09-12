package com.chenbaolu.qflt;

import android.content.Context;
import android.content.SharedPreferences;

import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.qflt.SQLite.MyDatabaseHelper;

import dagger.hilt.android.HiltAndroidApp;


/**
 * 描述 :
 * 创建时间 : 2022/9/8 08:49
 * 作者 : 23128
 */
@HiltAndroidApp
public class MyApplication extends BaseApplication {

    private static MyDatabaseHelper myDatabaseHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        myDatabaseHelper = new MyDatabaseHelper(this,"user_data",null,1);
    }

    public static MyDatabaseHelper getMyDatabaseHelper() {
        return myDatabaseHelper;
    }

    public static void setMyDatabaseHelper(MyDatabaseHelper myDatabaseHelper) {
        myDatabaseHelper = myDatabaseHelper;
    }
}
