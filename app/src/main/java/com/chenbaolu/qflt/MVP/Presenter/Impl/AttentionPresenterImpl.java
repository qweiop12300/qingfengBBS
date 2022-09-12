package com.chenbaolu.qflt.MVP.Presenter.Impl;

import androidx.recyclerview.widget.RecyclerView;

import com.chenbaolu.baselib.CallBack.LoadTasksCallBack;
import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.pojo.UserAttention;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.qflt.MVP.API.UserAPI;
import com.chenbaolu.qflt.MVP.Presenter.AttentionPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * 描述 :
 * 创建时间 : 2022/9/12 19:41
 * 作者 : 23128
 */
public class AttentionPresenterImpl implements AttentionPresenter.Model {
    AttentionPresenter.View view;

    @Inject
    public AttentionPresenterImpl() {
    }

    @Override
    public void setModel(BasePresenter.BaseView baseView) {
        view = (AttentionPresenter.View) baseView;
    }

    @Override
    public void getAttention(Integer pg) {
        UserAttention userAttention = new UserAttention();
        if(pg==0){
            userAttention.setUser_id(-1);
            userAttention.setFollowed_user_id(0);
            UserAPI.getUserAttention(userAttention, new LoadTasksCallBack<List<UserAttention>>() {
                @Override
                public void onSuccess(List<UserAttention> userAttentions) {
                    List<UserData> list = new ArrayList<>();
                    for (UserAttention userAttention:userAttentions){
                        list.add(userAttention.getUser_data());
                    }
                    view.showAttention(list);
                }

                @Override
                public void onStart() {
                    view.showLoading();
                }

                @Override
                public void onFailed(String message, Integer code) {
                    view.showAttention(null);
                }

                @Override
                public void onFinish() {
                    view.dissLoading();
                }
            });
        }else{
            userAttention.setUser_id(-1);
            userAttention.setFollowed_user_id(-1);
            UserAPI.getUserAttention(userAttention, new LoadTasksCallBack<List<UserAttention>>() {
                @Override
                public void onSuccess(List<UserAttention> userAttentions) {
                    List<UserData> list = new ArrayList<>();
                    for (UserAttention userAttention:userAttentions){
                        list.add(userAttention.getFan_user_data());
                    }
                    view.showFan(list);
                }

                @Override
                public void onStart() {
                    view.showLoading();
                }

                @Override
                public void onFailed(String message, Integer code) {
                    view.showFan(null);
                }

                @Override
                public void onFinish() {
                    view.dissLoading();
                }
            });
        }
    }
}
