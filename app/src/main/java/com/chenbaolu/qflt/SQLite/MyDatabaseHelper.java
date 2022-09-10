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

    String user_data = "CREATE TABLE `user_data` (\n" +
            "  `user_id` int DEFAULT NULL,\n" +
            "  `avatar` text ,\n" +
            "  `name` tinytext ,\n" +
            "  `sex` bit(1) ,\n" +
            "  `type` tinyint(1) DEFAULT '0',\n" +
            "  `create_date` datetime DEFAULT NULL ,\n" +
            "  `user_describe` text \n" +
            ")";

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(user_data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
