package com.chenbaolu.qflt.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.baselib.network.bean.pojo.NewsType;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.baselib.network.bean.pojo.UserNews;
import com.chenbaolu.qflt.MyApplication;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.rxjava3.internal.util.LinkedArrayList;

/**
 * 描述 :
 * 创建时间 : 2022/9/10 17:47
 * 作者 : 23128
 */
public class MyDBUtil {



    public static void setUserNews(UserNews userNews){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",userNews.getId());
        contentValues.put("user_id",userNews.getUser_id());
        contentValues.put("produce_user_id",userNews.getProduce_user_id());
        contentValues.put("type",userNews.getType());
        contentValues.put("post_id",userNews.getPost_id());
        contentValues.put("content",userNews.getContent());
        contentValues.put("create_date",userNews.getCreate_date().getTime());
        contentValues.put("type_text",userNews.getNews_type().getTitle());
        contentValues.put("is_view",userNews.getIs_view());
        MyApplication.getMyDatabaseHelper().getWritableDatabase().insert("user_news",null,contentValues);
    }

    public static List<UserNews> getUserNewsList(String where,String[] strings){
        Cursor cursor = MyApplication.getMyDatabaseHelper().getWritableDatabase().rawQuery("" +
                "select * from user_news "+
                "        where "+where+" order by create_date desc",strings);

        return getUserNews(cursor);
    }
    public static List<UserNews> getUserNewsListAsc(String where,String[] strings){
        Cursor cursor = MyApplication.getMyDatabaseHelper().getWritableDatabase().rawQuery("" +
                "select * from user_news "+
                "        where "+where+" order by create_date desc",strings);

        return getUserNews(cursor);
    }

    public static List<UserNews> getUserNewsTypeOne(String id){
        Cursor cursor = MyApplication.getMyDatabaseHelper().getWritableDatabase().rawQuery("select * from (select * from user_news where user_id != ? and type = 1 order by create_date desc) group by user_id \n" +
                "union \n" +
                "select * from (select * from user_news where user_id = ? and type =1 order by create_date desc ) group by produce_user_id",new String[]{id,id});
        return getUserNews(cursor);
    }


    public static List<UserNews> getUserNews(Cursor cursor){
        List<UserNews> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                UserNews userNews  = new UserNews();
                userNews.setId(cursor.getInt(0));
                userNews.setUser_id(cursor.getInt(1));
                userNews.setProduce_user_id(cursor.getInt(2));
                userNews.setType(cursor.getInt(3));
                userNews.setPost_id(cursor.getInt(4));
                userNews.setContent(cursor.getString(5));
                userNews.setCreate_date(new Timestamp(cursor.getLong(6)));
                userNews.setNews_type(new NewsType(userNews.getType(),cursor.getString(7)));
                userNews.setIs_view(cursor.getInt(8));
                list.add(userNews);
            }while (cursor.moveToNext());
        }
        return list;
    }


}
