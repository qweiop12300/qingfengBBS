package com.chenbaolu.qflt.MVP.Presenter;

import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.baselib.network.bean.pojo.PostCollects;
import com.chenbaolu.baselib.network.bean.pojo.PostComments;
import com.chenbaolu.baselib.network.bean.pojo.PostLike;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.baselib.network.bean.pojo.UserNews;

import java.util.List;
import java.util.Set;

/**
 * 描述 :
 * 创建时间 : 2022/9/16 22:33
 * 作者 : 23128
 */
public interface MinePostPresenter {
    interface View extends BasePresenter.BaseView {
        void showLike(List<PostLike> list);
        void showComments(List<PostComments> list);
        void showCollects(List<PostCollects> list);
        void showPost(List<Post> list);
    }
    interface Model extends BasePresenter.BaseModel{
        void getLike(long id);
        void getComments(long id);
        void getCollects(long id);
        void getPost(long id);
    }
}
