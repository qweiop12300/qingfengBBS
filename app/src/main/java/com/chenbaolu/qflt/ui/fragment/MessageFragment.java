package com.chenbaolu.qflt.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.chenbaolu.qflt.MVP.Presenter.MessagePresenter;
import com.chenbaolu.qflt.R;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * 描述 :
 * 创建时间 : 2022/9/7 21:02
 * 作者 : 23128
 */
@AndroidEntryPoint
public class MessageFragment extends Fragment implements MessagePresenter.View {
    @Inject
    MessagePresenter.Model model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_attention,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View rootView = getView();
        TextView textView = rootView.findViewById(R.id.default_action_bar_title);
        textView.setText("消息");

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dissLoading() {

    }
}
