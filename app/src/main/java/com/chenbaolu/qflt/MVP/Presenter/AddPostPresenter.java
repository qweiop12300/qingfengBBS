package com.chenbaolu.qflt.MVP.Presenter;

import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.dto.PostDto;
import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.baselib.network.bean.pojo.QiNiuToken;
import com.chenbaolu.baselib.network.bean.pojo.UserData;

import java.util.List;
import java.util.Set;

/**
 * 描述 :
 * 创建时间 : 2022/9/12 19:40
 * 作者 : 23128
 */
public interface AddPostPresenter {
    interface View extends BasePresenter.BaseView {
        void showQiNiuToken(QiNiuToken token);
        void showAddPost(String message,Integer code);
    }
    interface Model extends BasePresenter.BaseModel{
        void getQiNiuToken();
        void getAddPost(PostDto post);
    }
}
