package com.chenbaolu.qflt.MVP.Presenter.Impl;

import com.chenbaolu.baselib.CallBack.LoadTasksCallBack;
import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.baselib.network.bean.pojo.User;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.qflt.MVP.API.PostAPI;
import com.chenbaolu.qflt.MVP.API.UserAPI;
import com.chenbaolu.qflt.MVP.Presenter.UserDetailsPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * 描述 :
 * 创建时间 : 2022/9/16 21:40
 * 作者 : 23128
 */
public class UserDetailPresenterImpl implements UserDetailsPresenter.Model {
    private UserDetailsPresenter.View view;

    @Inject
    public UserDetailPresenterImpl() {
    }

    @Override
    public void getUserData(Long userId) {
        UserAPI.getUserData(userId, new LoadTasksCallBack<UserData>() {
            @Override
            public void onSuccess(UserData userData) {
                view.showUserData(userData);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.showUserData(null);
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }

    @Override
    public void getPostList(Integer pg, Integer pz, int type_id, int uid) {
        PostAPI.getListPost(pg, pz, type_id, uid, null,new LoadTasksCallBack<List<Post>>() {
            @Override
            public void onSuccess(List<Post> posts) {
                view.showPostList(posts);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.showPostList(null);
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }

    @Override
    public void attention(long id) {
        UserAPI.attention(id, new LoadTasksCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                view.attentionR(200);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.attentionR(code);
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }

    @Override
    public void setModel(BasePresenter.BaseView baseView) {
        view = (UserDetailsPresenter.View) baseView;
    }
}
