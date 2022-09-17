package com.chenbaolu.qflt.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.qflt.MVP.Presenter.MinePresenter;
import com.chenbaolu.qflt.R;
import com.chenbaolu.qflt.ui.activity.MinePostActivity;

import org.commonmark.node.Text;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 描述 :
 * 创建时间 : 2022/9/7 21:01
 * 作者 : 23128
 */
@AndroidEntryPoint
public class MineFragment extends Fragment implements MinePresenter.View {
    @Inject
    MinePresenter.Model model;

    private SwipeRefreshLayout swipeRefreshLayout;
    private CircleImageView circleImageView;
    private TextView name;
    private TextView attention;
    private TextView fan;
    private LinearLayout r1;
    private LinearLayout r2;
    private LinearLayout r3;
    private LinearLayout r4;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        model.setModel(this);

        View rootView = inflater.inflate(R.layout.fragment_mine,container,false);
        TextView textView = rootView.findViewById(R.id.default_action_bar_title);
        textView.setText("我的");
        swipeRefreshLayout = rootView.findViewById(R.id.mine_swipe);
        circleImageView = rootView.findViewById(R.id.mine_circler);
        name = rootView.findViewById(R.id.mine_user_name);
        attention = rootView.findViewById(R.id.mine_attention);
        fan = rootView.findViewById(R.id.mine_fan);
        r1 = rootView.findViewById(R.id.mine_r1);
        r2 = rootView.findViewById(R.id.mine_r2);
        r3 = rootView.findViewById(R.id.mine_r3);
        r4 = rootView.findViewById(R.id.mine_r4);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                init();
            }
        });
        init();
        return rootView;
    }


    void init(){
        model.getMyUserData();
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MinePostActivity.class);
                intent.putExtra("type","我的帖子");
                intent.putExtra("id", BaseApplication.getUserId());
                getContext().startActivity(intent);
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MinePostActivity.class);
                intent.putExtra("type","我的点赞");
                intent.putExtra("id", BaseApplication.getUserId());
                getContext().startActivity(intent);
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MinePostActivity.class);
                intent.putExtra("type","我的收藏");
                intent.putExtra("id", BaseApplication.getUserId());
                getContext().startActivity(intent);
            }
        });
        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MinePostActivity.class);
                intent.putExtra("type","我的评论");
                intent.putExtra("id", BaseApplication.getUserId());
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void dissLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showData(UserData userData) {
        Glide.with(getContext()).load(userData.getAvatar()).into(circleImageView);
        name.setText(userData.getName());
        attention.setText("关注  "+userData.getAttention_size());
        fan.setText("粉丝  "+userData.getFan_size());
    }

    @Override
    public void error(String message, Integer code) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }
}
