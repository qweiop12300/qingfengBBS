package com.chenbaolu.qflt.SQLite;

import android.content.ContentValues;
import android.database.Cursor;

import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.baselib.network.bean.pojo.NewsType;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.baselib.network.bean.pojo.UserNews;
import com.chenbaolu.qflt.MyApplication;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
        contentValues.put("sex",userData.getSex()?1:0);
        contentValues.put("type",userData.getType());
        contentValues.put("create_date",userData.getCreate_date().getTime());
        contentValues.put("user_describe",userData.getUser_describe());

        MyApplication.getMyDatabaseHelper().getWritableDatabase().insert("user_data",null,contentValues);
    }

    public static void setUserNews(UserNews userNews){
        if(getUserData(userNews.getUser_id())==null){
            setUserData(userNews.getUser_data());
        }
        if(getUserData(userNews.getProduce_user_id())==null){
            setUserData(userNews.getP_user_data());
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",userNews.getId());
        contentValues.put("user_id",userNews.getUser_id());
        contentValues.put("produce_user_id",userNews.getProduce_user_id());
        contentValues.put("type",userNews.getType());
        contentValues.put("post_id",userNews.getPost_id());
        contentValues.put("content",userNews.getContent());
        contentValues.put("create_date",userNews.getCreate_date().getTime());
        contentValues.put("type_text",userNews.getNews_type().getTitle());
        MyApplication.getMyDatabaseHelper().getWritableDatabase().insert("user_news",null,contentValues);
    }

    public static List<UserNews> getUserNewsList(int type){
        Cursor cursor = MyApplication.getMyDatabaseHelper().getWritableDatabase().rawQuery("" +
                "select un.id id,un.type type,un.produce_user_id produce_user_id,un.user_id user_id,un.create_date create_date,un.content content,un.post_id post_id,un.type_text type_text,\n" +
                "               u.user_id u_id,u.avatar u_avatar,u.name u_name,u.sex u_sex,u.type u_type,u.create_date u_cd,u.user_describe u_ud,\n" +
                "               p.user_id p_id,p.avatar p_avatar,p.name p_name,p.sex p_sex,p.type p_type,p.create_date p_cd,p.user_describe p_ud\n" +
                "        from user_news un\n" +
                "                 left join user_data u on u.user_id = un.user_id\n" +
                "                 left join user_data p on p.user_id = un.produce_user_id\n" +
                "        where un.type = ?;",new String[]{String.valueOf(type)});

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
                userNews.setUser_data(new UserData(cursor.getInt(8),cursor.getString(9),cursor.getString(10),cursor.getInt(11)==0?false:true,cursor.getInt(12),new Timestamp(cursor.getLong(13)),cursor.getString(14)));
                userNews.setP_user_data(new UserData(cursor.getInt(15),cursor.getString(16),cursor.getString(17),cursor.getInt(18)==0?false:true,cursor.getInt(19),new Timestamp(cursor.getLong(20)),cursor.getString(21)));
            }while (cursor.moveToNext());
        }
        return list;
    }


    public static UserData getUserData(long id){
        UserData userData = null;
        Cursor cursor = MyApplication.getMyDatabaseHelper().getWritableDatabase().rawQuery("select * from user_data where user_id = ?",new String[]{String.valueOf(id)});
        if(cursor.moveToFirst()){
            do {
                userData = new UserData();
                userData.setUser_id(cursor.getInt(0));
                userData.setAvatar(cursor.getString(1));
                userData.setName(cursor.getString(2));
                userData.setSex(cursor.getInt(3)==0?false:true);
                userData.setType(cursor.getInt(4));
                userData.setCreate_date(new Timestamp(cursor.getLong(5)));
                userData.setUser_describe(cursor.getString(6));

            }while (cursor.moveToNext());
        }
        return userData;
    }


}
