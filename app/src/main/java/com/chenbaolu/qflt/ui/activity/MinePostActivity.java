package com.chenbaolu.qflt.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chenbaolu.baselib.base.BaseActivity;
import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.baselib.network.bean.pojo.PostCollects;
import com.chenbaolu.baselib.network.bean.pojo.PostComments;
import com.chenbaolu.baselib.network.bean.pojo.PostLike;
import com.chenbaolu.qflt.Adapter.HomeRecyclerViewAdapter;
import com.chenbaolu.qflt.Adapter.MinePostRecyclerAdapter;
import com.chenbaolu.qflt.MVP.Presenter.MinePostPresenter;
import com.chenbaolu.qflt.R;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MinePostActivity extends BaseActivity implements MinePostPresenter.View {

    @Inject
    MinePostPresenter.Model model;

    RecyclerView recyclerView;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_post);

        model.setModel(this);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        Long id = intent.getLongExtra("id",0);

        recyclerView = findViewById(R.id.mine_post_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout = findViewById(R.id.mine_post_swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                init(type,id);
            }
        });

        init(type,id);

    }

    void init(String type,Long id){
        if(type!=null){
            ((TextView)findViewById(R.id.tool_bar_title)).setText(type);
            findViewById(R.id.tool_bar_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            switch (type){
                case "我的帖子" :
                    model.getPost(id);
                    break;
                case "我的点赞" :
                    model.getLike(id);
                    break;
                case "我的收藏" :
                    model.getCollects(id);
                    break;
                case "我的评论" :
                    model.getComments(id);
                    break;
            }
        }
    }

    @Override
    public void showLike(List<PostLike> list) {
        if(list!=null){
            recyclerView.setAdapter(new MinePostRecyclerAdapter(this,list));
        }
    }

    @Override
    public void showComments(List<PostComments> list) {
        if(list!=null){
            recyclerView.setAdapter(new MinePostRecyclerAdapter(this,list));
        }
    }

    @Override
    public void showCollects(List<PostCollects> list) {
        if(list!=null){
            recyclerView.setAdapter(new MinePostRecyclerAdapter(this,list));
        }
    }

    @Override
    public void showPost(List<Post> list) {
        if(list!=null){
            recyclerView.setAdapter(new HomeRecyclerViewAdapter(list));
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