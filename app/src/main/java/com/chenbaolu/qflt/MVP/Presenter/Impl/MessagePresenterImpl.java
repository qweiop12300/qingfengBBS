package com.chenbaolu.qflt.MVP.Presenter.Impl;

import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.qflt.MVP.Presenter.MessagePresenter;

import javax.inject.Inject;

/**
 * 描述 :
 * 创建时间 : 2022/9/12 19:40
 * 作者 : 23128
 */
public class MessagePresenterImpl implements MessagePresenter.Model {
    MessagePresenter.View view;
    @Inject
    public MessagePresenterImpl() {
    }

    @Override
    public void setModel(BasePresenter.BaseView baseView) {
        view = (MessagePresenter.View) baseView;
    }
}
