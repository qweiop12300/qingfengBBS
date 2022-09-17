package com.chenbaolu.qflt.MVP.Presenter;

import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.dto.PostCommentsDto;
import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.baselib.network.bean.pojo.PostComments;
import com.chenbaolu.baselib.network.bean.pojo.UserAttention;
import com.chenbaolu.baselib.network.bean.pojo.UserData;

import java.util.List;

/**
 * 描述 :
 * 创建时间 : 2022/9/16 21:37
 * 作者 : 23128
 */
public interface UserDetailsPresenter {
    interface View extends BasePresenter.BaseView{
        void showPostList(List<Post> list);
        void showUserData(UserData userData);
        void attentionR(int code);
    }
    interface Model extends BasePresenter.BaseModel{
        void getUserData(Long userId);
        void getPostList(Integer pg, Integer pz, int type_id,int uid);
        void attention(long id);
    }
}
