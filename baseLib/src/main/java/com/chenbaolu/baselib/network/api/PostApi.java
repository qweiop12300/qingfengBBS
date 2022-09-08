package com.chenbaolu.baselib.network.api;

import com.chenbaolu.baselib.network.bean.BaseResult;
import com.chenbaolu.baselib.network.bean.dto.PostCommentsDto;
import com.chenbaolu.baselib.network.bean.dto.PostDto;
import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.baselib.network.bean.pojo.PostComments;
import com.chenbaolu.baselib.network.bean.pojo.PostType;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * 描述 :
 * 创建时间 : 2022/9/8 10:28
 * 作者 : 23128
 */
public interface PostApi {
    @POST("setPost")
    Observable<BaseResult<String>> setPost(@Body PostDto postDto);
    @POST("getListPost")
    Observable<BaseResult<List<Post>>> getListPost(@Field("pg") Integer pg, @Field("pz") Integer pz, @Field("type_id") int type_id);
    @POST("setPostComments")
    Observable<BaseResult<String>> setPostComments(@Body PostCommentsDto postCommentsDto);
    @POST("getPostComments")
    Observable<BaseResult<List<PostComments>>> getPostComments(@Field("postId") Long postId);
    @POST("getPost")
    Observable<BaseResult<Post>> getPost(@Field("postId") Long postId);
    @POST("like")
    Observable<BaseResult<String>> like(@Field("postId") Long postId);
    @POST("collects")
    Observable<BaseResult<String>> collects(@Field("postId") Long postId);
    @POST("commentsLike")
    Observable<BaseResult<String>> commentsLike(@Field("postId") Long postId,@Field("commentsId") Long commentsId);
    @POST("getPostType")
    Observable<BaseResult<List<PostType>>> getPostType();
    @POST("upPost")
    Observable<BaseResult<String>> upPost(@Body PostDto postDto);
    @POST("dePost")
    Observable<BaseResult<String>> dePost(@Field("postId") Long postId);
}