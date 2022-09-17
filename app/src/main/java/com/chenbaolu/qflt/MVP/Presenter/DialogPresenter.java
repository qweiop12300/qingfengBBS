package com.chenbaolu.qflt.MVP.Presenter;

import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.baselib.network.bean.pojo.UserNews;

import java.util.List;
import java.util.Set;

/**
 * 描述 :
 * 创建时间 : 2022/9/12 19:40
 * 作者 : 23128
 */
public interface DialogPresenter {
    interface View extends BasePresenter.BaseView {
        void showUserDataList(List<UserData> list);
    }
    interface Model extends BasePresenter.BaseModel{
        void getUserDataList(Set<Long> list);
    }
}
