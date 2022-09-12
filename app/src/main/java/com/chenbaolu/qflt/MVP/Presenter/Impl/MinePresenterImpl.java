package com.chenbaolu.qflt.MVP.Presenter.Impl;

import com.chenbaolu.baselib.CallBack.LoadTasksCallBack;
import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.qflt.MVP.API.UserAPI;
import com.chenbaolu.qflt.MVP.Presenter.MinePresenter;

import javax.inject.Inject;

/**
 * 描述 :
 * 创建时间 : 2022/9/12 19:39
 * 作者 : 23128
 */
public class MinePresenterImpl implements MinePresenter.Model {
    MinePresenter.View view;

    @Inject
    public MinePresenterImpl() {
    }

    @Override
    public void setModel(BasePresenter.BaseView baseView) {
        view = (MinePresenter.View) baseView;
    }

    @Override
    public void getMyUserData() {
        UserAPI.getMyUserData(new LoadTasksCallBack<UserData>() {
            @Override
            public void onSuccess(UserData userData) {
                view.showData(userData);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.error(message,code);
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }
}
