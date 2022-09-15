package com.chenbaolu.qflt.MVP.Presenter.Impl;

import androidx.recyclerview.widget.RecyclerView;

import com.chenbaolu.baselib.CallBack.LoadTasksCallBack;
import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.baselib.network.bean.pojo.PostType;
import com.chenbaolu.qflt.MVP.API.PostAPI;
import com.chenbaolu.qflt.MVP.Presenter.HomePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * 描述 :
 * 创建时间 : 2022/9/8 21:20
 * 作者 : 23128
 */

public class HomePresenterImpl implements HomePresenter.Model {
    HomePresenter.View view;

    @Inject
    public HomePresenterImpl() {
    }

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
    public void getListPost(Integer pg, Integer pz, int type_id, RecyclerView.Adapter adapter) {
        PostAPI.getListPost(pg, pz, type_id, new LoadTasksCallBack<List<Post>>() {
            @Override
            public void onSuccess(List<Post> posts) {
                boolean isAdd = pg==0?false:true;
                view.updateRecyclerViewData(isAdd, posts, adapter);
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
    public void setModel(BasePresenter.BaseView baseView) {
        this.view = (HomePresenter.View) baseView;
    }

}
