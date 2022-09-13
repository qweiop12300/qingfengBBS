package com.chenbaolu.qflt;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.baselib.network.bean.SocketBean.Message;
import com.chenbaolu.baselib.network.bean.pojo.UserNews;
import com.chenbaolu.qflt.RxBus.RxBus;
import com.chenbaolu.qflt.SQLite.MyDBUtil;
import com.chenbaolu.qflt.SQLite.MyDatabaseHelper;

import dagger.hilt.android.HiltAndroidApp;
import io.reactivex.rxjava3.functions.Consumer;


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
        myDatabaseHelper = new MyDatabaseHelper(this,"my_db",null,1);
        RxBus.getInstance().toObservable(Message.class).subscribe(new Consumer<Message>() {
            @Override
            public void accept(Message message) throws Throwable {
                Log.d("test1",message.toString());
                if(message.getType().equals("send")){
                    for (UserNews userNews:message.getData().getNews()){
                        Log.d("test1",userNews.toString());
                        MyDBUtil.setUserNews(userNews);
                    }
                }
            }
        });
    }

    public static MyDatabaseHelper getMyDatabaseHelper() {
        return myDatabaseHelper;
    }

    public static void setMyDatabaseHelper(MyDatabaseHelper myDatabaseHelper) {
        myDatabaseHelper = myDatabaseHelper;
    }
}
