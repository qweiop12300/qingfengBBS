package com.chenbaolu.qflt.MVP.Presenter;

import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.pojo.UserData;

import javax.inject.Inject;

/**
 * 描述 :
 * 创建时间 : 2022/9/12 19:40
 * 作者 : 23128
 */
public interface MinePresenter {
    interface View extends BasePresenter.BaseView {
        void showData(UserData userData);
        void error(String message, Integer code);
    }
    interface Model extends BasePresenter.BaseModel{
        void getMyUserData();
    }
}
