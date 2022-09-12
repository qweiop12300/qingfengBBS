package com.chenbaolu.qflt.MVP.Presenter.module;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.chenbaolu.qflt.MVP.Presenter.Impl.HomePresenterImpl;
import com.chenbaolu.qflt.MVP.Presenter.Impl.LoginPresenterImpl;
import com.chenbaolu.qflt.MVP.Presenter.Impl.PostDetailsPresenterImpl;
import com.chenbaolu.qflt.MVP.Presenter.LoginPresenter;
import com.chenbaolu.qflt.MVP.Presenter.PostDetailsPresenter;
import com.chenbaolu.qflt.ui.activity.LoginActivity;
import com.chenbaolu.qflt.ui.activity.PostDetailsActivity;
import com.chenbaolu.qflt.ui.fragment.HomeFragment;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.qualifiers.ActivityContext;

/**
 * 描述 :
 * 创建时间 : 2022/9/12 15:23
 * 作者 : 23128
 */
@Module
@InstallIn(ActivityComponent.class)
public abstract class ActivityPresenterModule {


    @Binds
    public abstract LoginPresenter.Model bindLoginModel(LoginPresenterImpl loginPresenter);

    @Binds
    public abstract PostDetailsPresenter.Model bindPostDetailsModel(PostDetailsPresenterImpl postDetailsPresenter);

}
