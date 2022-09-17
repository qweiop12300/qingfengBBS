package com.chenbaolu.qflt.MVP.Presenter.Impl;

import com.chenbaolu.baselib.CallBack.LoadTasksCallBack;
import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.baselib.network.bean.pojo.UserNews;
import com.chenbaolu.qflt.MVP.API.UserAPI;
import com.chenbaolu.qflt.MVP.Presenter.DialogPresenter;
import com.chenbaolu.qflt.MVP.Presenter.MessagePresenter;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

/**
 * 描述 :
 * 创建时间 : 2022/9/12 19:40
 * 作者 : 23128
 */
public class DialogPresenterImpl implements DialogPresenter.Model {
    DialogPresenter.View view;
    @Inject
    public DialogPresenterImpl() {
    }

    @Override
    public void setModel(BasePresenter.BaseView baseView) {
        view = (DialogPresenter.View) baseView;
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

}
