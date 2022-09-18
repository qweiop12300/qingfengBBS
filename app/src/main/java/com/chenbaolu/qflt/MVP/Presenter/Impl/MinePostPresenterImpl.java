package com.chenbaolu.qflt.MVP.Presenter.Impl;

import com.chenbaolu.baselib.CallBack.LoadTasksCallBack;
import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.api.UserApi;
import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.baselib.network.bean.pojo.PostCollects;
import com.chenbaolu.baselib.network.bean.pojo.PostComments;
import com.chenbaolu.baselib.network.bean.pojo.PostLike;
import com.chenbaolu.qflt.MVP.API.PostAPI;
import com.chenbaolu.qflt.MVP.API.UserAPI;
import com.chenbaolu.qflt.MVP.Presenter.MinePostPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * 描述 :
 * 创建时间 : 2022/9/16 22:33
 * 作者 : 23128
 */
public class MinePostPresenterImpl implements MinePostPresenter.Model {

    private MinePostPresenter.View view;

    @Inject
    public MinePostPresenterImpl() {
    }

    @Override
    public void getLike(long id) {
        PostAPI.getPostLike(id, new LoadTasksCallBack<List<PostLike>>() {
            @Override
            public void onSuccess(List<PostLike> postLikes) {
                view.showLike(postLikes);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.showLike(null);
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }

    @Override
    public void getComments(long id) {
        PostAPI.getPostComments(0L, id, new LoadTasksCallBack<List<PostComments>>() {
            @Override
            public void onSuccess(List<PostComments> list) {
                view.showComments(list);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.showComments(null);
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }

    @Override
    public void getCollects(long id) {
        PostAPI.getPostCollects(id, new LoadTasksCallBack<List<PostCollects>>() {
            @Override
            public void onSuccess(List<PostCollects> list) {
                view.showCollects(list);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.showCollects(null);
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }

    @Override
    public void getPost(long id) {
        PostAPI.getListPost(0, 100, 0, (int) id, null,new LoadTasksCallBack<List<Post>>() {
            @Override
            public void onSuccess(List<Post> list) {
                view.showPost(list);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.showPost(null);
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }

    @Override
    public void setModel(BasePresenter.BaseView baseView) {
        view = (MinePostPresenter.View) baseView;
    }
}
