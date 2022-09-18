package com.chenbaolu.qflt.MVP.Presenter.module;

import com.chenbaolu.qflt.MVP.Presenter.AttentionPresenter;
import com.chenbaolu.qflt.MVP.Presenter.HomePresenter;
import com.chenbaolu.qflt.MVP.Presenter.Impl.AttentionPresenterImpl;
import com.chenbaolu.qflt.MVP.Presenter.Impl.HomePresenterImpl;
import com.chenbaolu.qflt.MVP.Presenter.Impl.MessagePresenterImpl;
import com.chenbaolu.qflt.MVP.Presenter.Impl.MinePresenterImpl;
import com.chenbaolu.qflt.MVP.Presenter.MessagePresenter;
import com.chenbaolu.qflt.MVP.Presenter.MinePresenter;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.FragmentComponent;

/**
 * 描述 :
 * 创建时间 : 2022/9/12 19:37
 * 作者 : 23128
 */
@Module
@InstallIn(FragmentComponent.class)
public abstract class FragmentPresenterModule {


    @Binds
    public abstract HomePresenter.Model bindHomeModel(HomePresenterImpl homePresenter);

    @Binds
    public abstract MinePresenter.Model bindMineModel(MinePresenterImpl minePresenter);

    @Binds
    public abstract MessagePresenter.Model bindMessageModel(MessagePresenterImpl messagePresenter);

    @Binds
    public abstract AttentionPresenter.Model bindAttentionModel(AttentionPresenterImpl attentionPresenter);

}
