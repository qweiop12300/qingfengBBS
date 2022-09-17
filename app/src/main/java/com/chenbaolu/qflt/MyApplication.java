package com.chenbaolu.qflt;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.Target;
import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.baselib.network.bean.SocketBean.Message;
import com.chenbaolu.baselib.network.bean.pojo.UserNews;
import com.chenbaolu.qflt.RxBus.RxBus;
import com.chenbaolu.qflt.SQLite.MyDBUtil;
import com.chenbaolu.qflt.SQLite.MyDatabaseHelper;
import com.chenbaolu.qflt.ui.activity.PostDetailsActivity;
import com.chenbaolu.qflt.ui.service.MessageService;

import dagger.hilt.android.HiltAndroidApp;
import io.noties.markwon.Markwon;
import io.noties.markwon.image.AsyncDrawable;
import io.noties.markwon.image.glide.GlideImagesPlugin;
import io.reactivex.rxjava3.functions.Consumer;


/**
 * 描述 :
 * 创建时间 : 2022/9/8 08:49
 * 作者 : 23128
 */
@HiltAndroidApp
public class MyApplication extends BaseApplication {

    private static MyDatabaseHelper myDatabaseHelper;

    private static Markwon markwon;
    @Override
    public void onCreate() {
        super.onCreate();

        markwon = Markwon.builder(this)
                // automatically create Glide instance
                .usePlugin(GlideImagesPlugin.create(this))
                // use supplied Glide instance
                .usePlugin(GlideImagesPlugin.create(Glide.with(this)))
                // if you need more control
                .usePlugin(GlideImagesPlugin.create(new GlideImagesPlugin.GlideStore() {
                    @NonNull
                    @Override
                    public RequestBuilder<Drawable> load(@NonNull AsyncDrawable drawable) {
                        return Glide.with(MyApplication.this).load(drawable.getDestination());
                    }

                    @Override
                    public void cancel(@NonNull Target<?> target) {
                        Glide.with(MyApplication.this).clear(target);
                    }
                }))
                .build();

        myDatabaseHelper = new MyDatabaseHelper(this,"my_db",null,1);
        RxBus.getInstance().toObservable(Message.class).subscribe(new Consumer<Message>() {
            @Override
            public void accept(Message message) throws Throwable {
                if(message.getType().equals("send")||message.getType().equals("mySend")){
                    for (UserNews userNews:message.getData().getNews()){
                        Log.d("test10",userNews.toString());
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

    public static Markwon getMarkwon() {
        return markwon;
    }

    public static void setMarkwon(Markwon mark) {
        markwon = mark;
    }
}
