package com.chenbaolu.qflt.MVP.Presenter.Impl;

import com.chenbaolu.baselib.CallBack.LoadTasksCallBack;
import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.dto.PostDto;
import com.chenbaolu.baselib.network.bean.pojo.QiNiuToken;
import com.chenbaolu.qflt.MVP.API.PostAPI;
import com.chenbaolu.qflt.MVP.API.UserAPI;
import com.chenbaolu.qflt.MVP.Presenter.AddPostPresenter;

import javax.inject.Inject;

/**
 * 描述 :
 * 创建时间 : 2022/9/12 19:40
 * 作者 : 23128
 */
public class AddPostPresenterImpl implements AddPostPresenter.Model {
    AddPostPresenter.View view;
    @Inject
    public AddPostPresenterImpl() {
    }

    @Override
    public void setModel(BasePresenter.BaseView baseView) {
        view = (AddPostPresenter.View) baseView;
    }

    @Override
    public void getQiNiuToken() {
        UserAPI.getQiNiuToken(new LoadTasksCallBack<QiNiuToken>() {
            @Override
            public void onSuccess(QiNiuToken qiNiuToken) {
                view.showQiNiuToken(qiNiuToken);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.showQiNiuToken(null);
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }

    @Override
    public void getAddPost(PostDto post) {
        PostAPI.setPost(post, new LoadTasksCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                view.showAddPost(s,200);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.showAddPost(message, code);
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }
}
