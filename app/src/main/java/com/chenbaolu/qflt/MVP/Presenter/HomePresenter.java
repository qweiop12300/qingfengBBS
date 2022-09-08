package com.chenbaolu.qflt.MVP.Presenter;

import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.pojo.PostType;

import java.util.List;

/**
 * 描述 :
 * 创建时间 : 2022/9/8 21:08
 * 作者 : 23128
 */
public interface HomePresenter {
    interface View extends BasePresenter.BaseView {
        void initTabLayout(List<PostType> list);
    }
    interface Model extends BasePresenter.BaseModel{
        void getPostType();
    }
}
