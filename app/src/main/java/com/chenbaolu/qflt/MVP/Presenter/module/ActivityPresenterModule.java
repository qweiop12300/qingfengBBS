package com.chenbaolu.qflt.MVP.Presenter.module;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.chenbaolu.qflt.MVP.Presenter.AddPostPresenter;
import com.chenbaolu.qflt.MVP.Presenter.DialogPresenter;
import com.chenbaolu.qflt.MVP.Presenter.Impl.AddPostPresenterImpl;
import com.chenbaolu.qflt.MVP.Presenter.Impl.DialogPresenterImpl;
import com.chenbaolu.qflt.MVP.Presenter.Impl.HomePresenterImpl;
import com.chenbaolu.qflt.MVP.Presenter.Impl.LoginPresenterImpl;
import com.chenbaolu.qflt.MVP.Presenter.Impl.MessagePresenterImpl;
import com.chenbaolu.qflt.MVP.Presenter.Impl.MinePostPresenterImpl;
import com.chenbaolu.qflt.MVP.Presenter.Impl.PostDetailsPresenterImpl;
import com.chenbaolu.qflt.MVP.Presenter.Impl.UserDetailPresenterImpl;
import com.chenbaolu.qflt.MVP.Presenter.LoginPresenter;
import com.chenbaolu.qflt.MVP.Presenter.MessagePresenter;
import com.chenbaolu.qflt.MVP.Presenter.MinePostPresenter;
import com.chenbaolu.qflt.MVP.Presenter.PostDetailsPresenter;
import com.chenbaolu.qflt.MVP.Presenter.UserDetailsPresenter;
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
    public abstract DialogPresenter.Model bindDialogModel(DialogPresenterImpl dialogPresenter);

    @Binds
    public abstract LoginPresenter.Model bindLoginModel(LoginPresenterImpl loginPresenter);

    @Binds
    public abstract PostDetailsPresenter.Model bindPostDetailsModel(PostDetailsPresenterImpl postDetailsPresenter);

    @Binds
    public abstract UserDetailsPresenter.Model bindUserDetailsModel(UserDetailPresenterImpl userDetailPresenter);

    @Binds
    public abstract MinePostPresenter.Model bindMinePostModel(MinePostPresenterImpl userDetailPresenter);

    @Binds
    public abstract AddPostPresenter.Model bindAddPostModel(AddPostPresenterImpl addPostPresenter);
}
