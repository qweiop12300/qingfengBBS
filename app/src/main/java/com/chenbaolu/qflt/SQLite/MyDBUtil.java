package com.chenbaolu.qflt.SQLite;

import android.content.ContentValues;

import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.qflt.MyApplication;

/**
 * 描述 :
 * 创建时间 : 2022/9/10 17:47
 * 作者 : 23128
 */
public class MyDBUtil {
    public static void setUserData(UserData userData){
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id",userData.getUser_id());
        contentValues.put("avatar",userData.getAvatar());
        contentValues.put("name",userData.getName());
        contentValues.put("sex",userData.getSex());
        contentValues.put("type",userData.getType());
        contentValues.put("user_describe",userData.getUser_describe());
        MyApplication.getMyDatabaseHelper().getWritableDatabase().insert("user_data",null,contentValues);
    }
}
