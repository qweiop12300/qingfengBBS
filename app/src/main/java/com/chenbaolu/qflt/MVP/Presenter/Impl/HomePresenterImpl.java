package com.chenbaolu.qflt.MVP.Presenter.Impl;

import com.chenbaolu.baselib.CallBack.LoadTasksCallBack;
import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.api.PostApi;
import com.chenbaolu.baselib.network.bean.pojo.PostType;
import com.chenbaolu.qflt.MVP.API.PostAPI;
import com.chenbaolu.qflt.MVP.Presenter.HomePresenter;

import java.util.List;

/**
 * 描述 :
 * 创建时间 : 2022/9/8 21:20
 * 作者 : 23128
 */
public class HomePresenterImpl implements HomePresenter.Model {
    HomePresenter.View view;

    @Override
    public void getPostType() {
        PostAPI.getPostType(new LoadTasksCallBack<List<PostType>>() {
            @Override
            public void onSuccess(List<PostType> list) {
                view.initTabLayout(list);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {

            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }

    @Override
    public void setBaseView(BasePresenter.BaseView baseView) {
        view=(HomePresenter.View) baseView;
    }
}
