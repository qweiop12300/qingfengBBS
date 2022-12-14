package com.chenbaolu.qflt.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.Target;
import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.baselib.base.BaseActivity;
import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.dto.PostCommentsDto;
import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.baselib.network.bean.pojo.PostComments;
import com.chenbaolu.baselib.network.bean.pojo.UserAttention;
import com.chenbaolu.qflt.Adapter.CommentsRecyclerViewAdapter;
import com.chenbaolu.qflt.CallBack.PostCommentsCallBack;
import com.chenbaolu.qflt.Dialog.PostCommentsBottomDialog;
import com.chenbaolu.qflt.MVP.Presenter.Impl.PostDetailsPresenterImpl;
import com.chenbaolu.qflt.MVP.Presenter.PostDetailsPresenter;
import com.chenbaolu.qflt.MyApplication;
import com.chenbaolu.qflt.R;
import com.chenbaolu.qflt.util.CommentsSort;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import de.hdodenhof.circleimageview.CircleImageView;
import io.noties.markwon.Markwon;
import io.noties.markwon.image.AsyncDrawable;
import io.noties.markwon.image.glide.GlideImagesPlugin;

@AndroidEntryPoint
public class PostDetailsActivity extends BaseActivity implements PostDetailsPresenter.View {

    private Long id;

    private TextView name;
    private TextView title;
    private TextView markdownView;
    private TextView commentsSize;
    private CircleImageView circleImageView;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView time;

    private ImageView like;
    private ImageView collects;
    private Button button;


    @Inject
    public PostDetailsPresenter.Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        model.setModel(this);
        Intent intent = getIntent();
        id = intent.getLongExtra("id",0);
        name = findViewById(R.id.back_action_bar_title);
        title = findViewById(R.id.post_details_title);
        markdownView = findViewById(R.id.post_details_mark);
        commentsSize = findViewById(R.id.post_comments_size);
        circleImageView = findViewById(R.id.back_action_bar_circle);
        recyclerView = findViewById(R.id.comments_rec);
        swipeRefreshLayout = findViewById(R.id.post_details_swipe);
        time = findViewById(R.id.post_details_time);

        button = findViewById(R.id.back_action_bar_attention);
        like = findViewById(R.id.post_like);
        collects = findViewById(R.id.post_star);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                init();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        findViewById(R.id.back_action_bar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        init();
    }

    public void init(){

        findViewById(R.id.post_comments).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostCommentsBottomDialog(PostDetailsActivity.this, "", new PostCommentsCallBack() {
                    @Override
                    public void setComments(PostCommentsDto postCommentsDto) {

                    }

                    @Override
                    public void backContent(String title) {
                        Log.d("test1","sddd");
                        PostCommentsDto postCommentsDto = new PostCommentsDto();
                        postCommentsDto.setPostId(id);
                        postCommentsDto.setTitle(title);
                        model.setPostComments(postCommentsDto);
                    }
                }).show();
            }
        });

        model.getPost(id);
        model.getPostComments(id);
    }

    public PostDetailsActivity() {
        super();
    }

    @Override
    public void showPost(Post post) {
        UserAttention userAttention = new UserAttention();
        userAttention.setUser_id(-1);
        userAttention.setFollowed_user_id(post.getUser_id());
        model.getUserAttention(userAttention);
        Glide.with(this).load(post.getUser_data().getAvatar()).into(circleImageView);
        name.setText(post.getUser_data().getName());
        title.setText(post.getTitle());
        time.setText(post.getCreate_date().toString());

        MyApplication.getMarkwon().setMarkdown(markdownView,post.getContent());

        if (post.getPcu()!=null){
            collects.setImageDrawable(getDrawable(R.drawable.ic_post_star2));
        }
        if(post.getPlu()!=null){
            like.setImageDrawable(getDrawable(R.drawable.ic_post_thumb2));
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.attention(post.getUser_id());
            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.like(id);
            }
        });
        collects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.collects(id);
            }
        });
    }

    @Override
    public void showPostComments(List<PostComments> postComments) {
        commentsSize.setText("??????("+postComments.size()+")");
        List<PostComments> list = CommentsSort.sort(postComments);
        CommentsRecyclerViewAdapter recyclerViewAdapter = new CommentsRecyclerViewAdapter(list, this, new PostCommentsCallBack() {
            @Override
            public void setComments(PostCommentsDto postCommentsDto) {
                model.setPostComments(postCommentsDto);
            }

            @Override
            public void backContent(String title) {

            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void setPostCommentsResult(String result) {
        if (result==null){
            Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
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

    @Override
    public void likeR(int code) {
        if(code==200){
            Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
            like.setImageDrawable(getDrawable(R.drawable.ic_post_thumb2));
            return;
        }
        Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
        like.setImageDrawable(getDrawable(R.drawable.ic_post_thumb));
    }

    @Override
    public void collectsR(int code) {
        if(code==200){
            Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
            collects.setImageDrawable(getDrawable(R.drawable.ic_post_star2));
            return;
        }
        Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
        collects.setImageDrawable(getDrawable(R.drawable.ic_post_star));
    }

    @Override
    public void attentionR(int code) {
        if(code==200){
            button.setText("?????????");
            button.setBackground(getDrawable(R.drawable.search_radius));
            Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
            return;
        }
        button.setText("??????");
        button.setBackground(getDrawable(R.drawable.button_radius));
        Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showAttention(int code) {
        if(code==200){
            button.setText("?????????");
            button.setBackground(getDrawable(R.drawable.search_radius));
        }
    }
}