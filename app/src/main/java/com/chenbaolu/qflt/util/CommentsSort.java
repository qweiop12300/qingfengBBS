package com.chenbaolu.qflt.util;

import android.util.Log;

import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.baselib.network.bean.pojo.PostComments;

import java.util.LinkedList;
import java.util.List;

/**
 * 描述 :
 * 创建时间 : 2022/9/11 21:00
 * 作者 : 23128
 */
public class CommentsSort {
    public static List<PostComments> sort(List<PostComments> list){
        Log.d("test1",list.toString());
        List<PostComments> result = new LinkedList<PostComments>();
        for (PostComments comments : list){
            if(comments.getReply_id()==0){
                comments.setLayers(0);
                result.add(comments);
            }else{
                for(int i=0;i<result.size();i++){
                    if(comments.getReply_id()==result.get(i).getId()){
                        comments.setLayers(result.get(i).getLayers()+1);
                        result.add(i+1,comments);
                        break;
                    }
                }
            }
        }
        Log.d("test1",result.toString());
        return result;
    }
}
