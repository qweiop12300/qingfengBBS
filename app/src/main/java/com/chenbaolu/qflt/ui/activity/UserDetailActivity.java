package com.chenbaolu.qflt.ui.activity;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.chenbaolu.baselib.base.BaseActivity;
import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.qflt.Adapter.HomeRecyclerViewAdapter;
import com.chenbaolu.qflt.MVP.Presenter.UserDetailsPresenter;
import com.chenbaolu.qflt.R;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import de.hdodenhof.circleimageview.CircleImageView;

@AndroidEntryPoint
public class UserDetailActivity extends BaseActivity implements UserDetailsPresenter.View {

    private Long id;
    @Inject
    UserDetailsPresenter.Model model;

    SwipeRefreshLayout swipeRefreshLayout;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        model.setModel(this);

        Intent intent = getIntent();
        id = intent.getLongExtra("id",0);

        swipeRefreshLayout = findViewById(R.id.user_details_swipe);

        findViewById(R.id.tool_bar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.user_details_attention).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.attention(id);
            }
        });
        findViewById(R.id.user_details_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserDetailActivity.this,DialogActivity.class);
                intent1.putExtra("id",id);
                startActivity(intent1);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                model.getUserData(id);
                model.getPostList(0,100,0, Math.toIntExact(id));
            }
        });

        model.getUserData(id);
        model.getPostList(0,100,0, Math.toIntExact(id));
    }

    @Override
    public void showPostList(List<Post> list) {
        RecyclerView recyclerView = findViewById(R.id.user_details_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new HomeRecyclerViewAdapter(list));
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void showUserData(UserData userData) {
        CircleImageView circleImageView = findViewById(R.id.user_details_circle);
        Glide.with(this).load(userData.getAvatar()).into(circleImageView);
        TextView textView = findViewById(R.id.user_details_name);
        textView.setText(userData.getName());
    }

    @Override
    public void attentionR(int code) {
        Button button = findViewById(R.id.user_details_attention);
        if (code==200){
            button.setBackground(getDrawable(R.drawable.search_radius));
            button.setText("已关注");
        }else {
            button.setBackground(getDrawable(R.drawable.button_radius));
            button.setText("关注");
        }
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void dissLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }
}