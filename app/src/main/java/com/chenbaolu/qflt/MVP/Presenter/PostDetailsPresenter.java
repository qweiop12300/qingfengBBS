package com.chenbaolu.qflt.MVP.Presenter;

import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.dto.PostCommentsDto;
import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.baselib.network.bean.pojo.PostComments;
import com.chenbaolu.baselib.network.bean.pojo.UserAttention;

import java.util.List;

/**
 * 描述 :
 * 创建时间 : 2022/9/11 11:05
 * 作者 : 23128
 */
public interface PostDetailsPresenter{
    interface View extends BasePresenter.BaseView{
        void showPost(Post post);
        void showPostComments(List<PostComments> postComments);
        void setPostCommentsResult(String result);
        void likeR(int code);
        void collectsR(int code);
        void attentionR(int code);
        void showAttention(int code);
    }
    interface Model extends BasePresenter.BaseModel{
        void getPost(long id);
        void getPostComments(long id);
        void setPostComments(PostCommentsDto postCommentsDto);
        void like(long id);
        void collects(long id);
        void attention(long id);
        void getUserAttention(UserAttention userAttention);
    }

}
