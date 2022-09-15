package com.chenbaolu.qflt.MVP.Presenter.Impl;

import com.chenbaolu.baselib.CallBack.LoadTasksCallBack;
import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.pojo.User;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.baselib.network.bean.pojo.UserNews;
import com.chenbaolu.qflt.MVP.API.UserAPI;
import com.chenbaolu.qflt.MVP.Presenter.MessagePresenter;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

/**
 * 描述 :
 * 创建时间 : 2022/9/12 19:40
 * 作者 : 23128
 */
public class MessagePresenterImpl implements MessagePresenter.Model {
    MessagePresenter.View view;
    @Inject
    public MessagePresenterImpl() {
    }

    @Override
    public void setModel(BasePresenter.BaseView baseView) {
        view = (MessagePresenter.View) baseView;
    }

    @Override
    public void getUserDataList(Set<Long> list) {
        UserAPI.getUserDataList(list, new LoadTasksCallBack<List<UserData>>() {
            @Override
            public void onSuccess(List<UserData> list) {
                view.showUserDataList(list);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.showUserDataList(null);
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }

    @Override
    public void getUserData(long id, UserNews userNews) {
        UserAPI.getUserData(id, new LoadTasksCallBack<UserData>() {
            @Override
            public void onSuccess(UserData userData) {
                view.showUserData(userData,userNews);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.showUserData(null,userNews);
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }
}
