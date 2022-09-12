package com.chenbaolu.qflt.MVP.Presenter;

import androidx.recyclerview.widget.RecyclerView;

import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.pojo.UserAttention;
import com.chenbaolu.baselib.network.bean.pojo.UserData;

import java.util.List;

/**
 * 描述 :
 * 创建时间 : 2022/9/12 19:41
 * 作者 : 23128
 */
public interface AttentionPresenter {
    interface View extends BasePresenter.BaseView {
        void showAttention(List<UserData> list);
        void showFan(List<UserData> list);
    }
    interface Model extends BasePresenter.BaseModel{
        void getAttention(Integer pg);
    }
}
