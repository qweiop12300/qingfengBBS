package com.chenbaolu.qflt.MVP.API;

import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.baselib.CallBack.BaseObserver;
import com.chenbaolu.baselib.CallBack.LoadTasksCallBack;
import com.chenbaolu.baselib.network.api.PostApi;
import com.chenbaolu.baselib.network.bean.BaseResult;
import com.chenbaolu.baselib.network.bean.dto.PostCommentsDto;
import com.chenbaolu.baselib.network.bean.dto.PostDto;
import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.baselib.network.bean.pojo.PostCollects;
import com.chenbaolu.baselib.network.bean.pojo.PostComments;
import com.chenbaolu.baselib.network.bean.pojo.PostLike;
import com.chenbaolu.baselib.network.bean.pojo.PostType;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 描述 :
 * 创建时间 : 2022/9/8 18:40
 * 作者 : 23128
 */

public class PostAPI {
    public static void setPost(PostDto postDto, LoadTasksCallBack<String> loadTasksCallBack){
        BaseApplication.getRetrofit().create(PostApi.class).setPost(postDto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }
    public static void getListPost(Integer pg,Integer pz, int type_id,int uid, LoadTasksCallBack<List<Post>> loadTasksCallBack){
        BaseApplication.getRetrofit().create(PostApi.class).getListPost(pg, pz, type_id,uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<List<Post>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }
    public static void setPostComments(PostCommentsDto postCommentsDto, LoadTasksCallBack<String> loadTasksCallBack){
        BaseApplication.getRetrofit().create(PostApi.class).setPostComments(postCommentsDto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }
    public static void getPostComments(Long postId, Long userId,LoadTasksCallBack<List<PostComments>> loadTasksCallBack){
        BaseApplication.getRetrofit().create(PostApi.class).getPostComments(postId,userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<List<PostComments>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }

    public static void getPostLike(Long userId, LoadTasksCallBack<List<PostLike>> loadTasksCallBack){
        BaseApplication.getRetrofit().create(PostApi.class).getPostLike(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<List<PostLike>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }

    public static void getPostCollects(Long postId, LoadTasksCallBack<List<PostCollects>> loadTasksCallBack){
        BaseApplication.getRetrofit().create(PostApi.class).getPostCollects(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<List<PostCollects>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }

    public static void getPost(Long postId, LoadTasksCallBack<Post> loadTasksCallBack){
        BaseApplication.getRetrofit().create(PostApi.class).getPost(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<Post>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }
    public static void like(Long postId, LoadTasksCallBack<String> loadTasksCallBack){
        BaseApplication.getRetrofit().create(PostApi.class).like(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }
    public static void collects(Long postId, LoadTasksCallBack<String> loadTasksCallBack){
        BaseApplication.getRetrofit().create(PostApi.class).collects(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }
    public static void commentsLike(Long post,Long id ,LoadTasksCallBack<String> loadTasksCallBack){
        BaseApplication.getRetrofit().create(PostApi.class).commentsLike(post,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }
    public static void getPostType(LoadTasksCallBack<List<PostType>> loadTasksCallBack){
        BaseApplication.getRetrofit().create(PostApi.class).getPostType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<List<PostType>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }
    public static void upPost(PostDto postDto, LoadTasksCallBack<String> loadTasksCallBack){
        BaseApplication.getRetrofit().create(PostApi.class).upPost(postDto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }
    public static void dePost(Long postDto, LoadTasksCallBack<String> loadTasksCallBack){
        BaseApplication.getRetrofit().create(PostApi.class).dePost(postDto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }
}
