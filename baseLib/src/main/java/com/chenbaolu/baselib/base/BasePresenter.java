package com.chenbaolu.baselib.base;

/**
 * 描述 :
 * 创建时间 : 2022/9/8 10:49
 * 作者 : 23128
 */
public interface BasePresenter {
    interface BaseView{
        void showLoading();
        void dissLoading();
    }
    interface BaseModel{
        void setModel(BasePresenter.BaseView baseView);
    }
}
