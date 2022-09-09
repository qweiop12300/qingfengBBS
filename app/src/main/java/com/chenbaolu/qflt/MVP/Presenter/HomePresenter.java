package com.chenbaolu.qflt.MVP.Presenter;

import androidx.recyclerview.widget.RecyclerView;

import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.pojo.Post;
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
        void updateRecyclerViewData(boolean isAdd,List<Post> posts,RecyclerView.Adapter adapter);
    }
    interface Model extends BasePresenter.BaseModel{
        void getPostType();
        void getListPost(Integer pg, Integer pz, int type_id, RecyclerView.Adapter adapter);
    }
}
