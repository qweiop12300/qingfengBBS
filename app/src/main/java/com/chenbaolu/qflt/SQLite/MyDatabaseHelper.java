package com.chenbaolu.qflt.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * 描述 :
 * 创建时间 : 2022/9/10 10:26
 * 作者 : 23128
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    private String user_news = "create table `user_news`(\n" +
            "                            `id` int,\n" +
            "                            `user_id` int,\n" +
            "                            `produce_user_id` int ,\n" +
            "                            `type` int,\n" +
            "                            `post_id` int ,\n" +
            "                            `content` text ,\n" +
            "                            `create_date` INTEGER,\n" +
            "                            `type_text` text,\n"+
            "                            `is_view` int \n"+
            ")";




    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(user_news);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
