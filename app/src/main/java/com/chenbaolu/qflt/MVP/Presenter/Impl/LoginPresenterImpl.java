package com.chenbaolu.qflt.MVP.Presenter.Impl;

import com.chenbaolu.baselib.CallBack.LoadTasksCallBack;
import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.dto.UserDto;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.qflt.MVP.API.UserAPI;
import com.chenbaolu.qflt.MVP.Presenter.LoginPresenter;
import com.chenbaolu.qflt.ui.activity.LoginActivity;

import javax.inject.Inject;

/**
 * 描述 :
 * 创建时间 : 2022/9/9 21:28
 * 作者 : 23128
 */
public class LoginPresenterImpl implements LoginPresenter.Model {
    LoginPresenter.View view;

    @Inject
    public LoginPresenterImpl() {
    }

    @Override
    public void login(UserDto userDto) {
        UserAPI.loginUser(userDto, new LoadTasksCallBack<UserData>() {
            @Override
            public void onSuccess(UserData userData) {
                view.loginSuccess(userData);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.loginFailed(message, code);
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }

    @Override
    public void registered(UserDto userDto) {
        UserAPI.registerUser(userDto, new LoadTasksCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                view.registeredSuccess(s);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.registeredFailed(message, code);
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }

    @Override
    public void setModel(BasePresenter.BaseView baseView) {
        view = (LoginPresenter.View) baseView;
    }
}
