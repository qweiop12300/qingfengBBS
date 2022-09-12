package com.chenbaolu.qflt.MVP.Presenter.Impl;

import android.util.Log;
import android.widget.Toast;

import com.chenbaolu.baselib.CallBack.LoadTasksCallBack;
import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.dto.PostCommentsDto;
import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.baselib.network.bean.pojo.PostComments;
import com.chenbaolu.baselib.network.bean.pojo.UserAttention;
import com.chenbaolu.qflt.MVP.API.PostAPI;
import com.chenbaolu.qflt.MVP.API.UserAPI;
import com.chenbaolu.qflt.MVP.Presenter.PostDetailsPresenter;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.List;

import javax.inject.Inject;

/**
 * 描述 :
 * 创建时间 : 2022/9/11 11:05
 * 作者 : 23128
 */
public class PostDetailsPresenterImpl implements PostDetailsPresenter.Model {
    private PostDetailsPresenter.View view;

    @Inject
    public PostDetailsPresenterImpl() {
    }

    @Override
    public void getPost(long id) {
        PostAPI.getPost(id, new LoadTasksCallBack<Post>() {
            @Override
            public void onSuccess(Post post) {
                view.showPost(post);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.dissLoading();
            }

            @Override
            public void onFinish() {

            }
        });
    }

    @Override
    public void getPostComments(long id) {
        PostAPI.getPostComments(id, new LoadTasksCallBack<List<PostComments>>() {
            @Override
            public void onSuccess(List<PostComments> postComments) {
                view.showPostComments(postComments);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.showPostComments(null);
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }

    @Override
    public void setPostComments(PostCommentsDto postCommentsDto) {
        PostAPI.setPostComments(postCommentsDto, new LoadTasksCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                Log.d("test1","ss");
                view.setPostCommentsResult(s);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.setPostCommentsResult(message);
                Log.d("test1","ssss");
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }



    @Override
    public void like(long id) {
        PostAPI.like(id, new LoadTasksCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                view.likeR(200);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.likeR(code);
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }

    @Override
    public void collects(long id) {
        PostAPI.collects(id, new LoadTasksCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                view.collectsR(200);
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.collectsR(code);
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
    public void getUserAttention(UserAttention userAttention) {
        UserAPI.getUserAttention(userAttention, new LoadTasksCallBack<List<UserAttention>>() {
            @Override
            public void onSuccess(List<UserAttention> userAttentions) {
                if (userAttentions.size()>0){
                    view.showAttention(200);
                }else{
                    view.showAttention(-1);
                }
            }

            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onFailed(String message, Integer code) {
                view.showAttention(code);
            }

            @Override
            public void onFinish() {
                view.dissLoading();
            }
        });
    }

    @Override
    public void setModel(BasePresenter.BaseView baseView) {
        view = (PostDetailsPresenter.View) baseView;
    }
}
