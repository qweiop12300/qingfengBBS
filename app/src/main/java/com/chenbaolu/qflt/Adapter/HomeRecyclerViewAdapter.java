package com.chenbaolu.qflt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.baselib.CallBack.LoadTasksCallBack;
import com.chenbaolu.baselib.network.bean.pojo.ImageStatus;
import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.qflt.CallBack.ListCallBack;
import com.chenbaolu.qflt.MVP.API.PostAPI;
import com.chenbaolu.qflt.R;
import com.chenbaolu.qflt.ui.activity.PostDetailsActivity;
import com.chenbaolu.qflt.ui.activity.UserDetailActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 描述 :
 * 创建时间 : 2022/9/4 19:08
 * 作者 : 23128
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private List<Post> list;
    private Context context = BaseApplication.getContext();

    public List<Post> getList() {
        return list;
    }

    public void setList(List<Post> list) {
        this.list = list;
    }

    public HomeRecyclerViewAdapter(List<Post> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_post_home,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post  = list.get(position);
        holder.textView.setText(post.getUser_data().getName());
        holder.title.setText(post.getTitle());
        holder.content.setText(post.getContent());
        holder.like_size.setText(String.valueOf(post.getLike()));
        holder.comments_size.setText(String.valueOf(post.getComments()));
        holder.collects_size.setText(String.valueOf(post.getCollects()));
        holder.like.setImageDrawable(post.getPlu()!=null?context.getDrawable(R.drawable.ic_post_thumb2):context.getDrawable(R.drawable.ic_post_thumb));
        holder.collects.setImageDrawable(post.getPcu()!=null?context.getDrawable(R.drawable.ic_post_star2):context.getDrawable(R.drawable.ic_post_star));
        Glide.with(context).load(post.getUser_data().getAvatar()).into(holder.circleImageView);
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        try {
            JSONArray jsonArray = new JSONArray(post.getImages());
            List<ImageStatus> imageStatuses = new ArrayList<>();
            for (int i=0;i<jsonArray.length();i++){
                imageStatuses.add(new ImageStatus(null,jsonArray.getString(i)));
            }
            holder.recyclerView.setAdapter(new ImageRecyclerViewAdapter(imageStatuses, context, new ListCallBack() {
                @Override
                public void click(String url) {

                }
            }));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PostDetailsActivity.class);
                intent.putExtra("id",post.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        ((RelativeLayout)holder.like.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostAPI.like(post.getId(), new LoadTasksCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        holder.like.setImageDrawable(context.getDrawable(R.drawable.ic_post_thumb2));
                        post.setLike(post.getLike()+1);
                        holder.like_size.setText(String.valueOf(post.getLike()));
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onFailed(String message, Integer code) {
                        if(code==202){
                            holder.like.setImageDrawable(context.getDrawable(R.drawable.ic_post_thumb));
                            post.setLike(post.getLike()-1);
                            holder.like_size.setText(String.valueOf(post.getLike()));
                        }else{
                            Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFinish() {

                    }
                });
            }
        });
        ((RelativeLayout)holder.collects.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostAPI.collects(post.getId(), new LoadTasksCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        holder.collects.setImageDrawable(context.getDrawable(R.drawable.ic_post_star2));
                        post.setCollects(post.getCollects()+1);
                        holder.collects_size.setText(String.valueOf(post.getCollects()));
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onFailed(String message, Integer code) {
                        if(code==202){
                            holder.collects.setImageDrawable(context.getDrawable(R.drawable.ic_post_star));
                            post.setCollects(post.getCollects()-1);
                            holder.collects_size.setText(String.valueOf(post.getCollects()));
                        }else{
                            Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFinish() {

                    }
                });
            }
        });

        View.OnClickListener click = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserDetailActivity.class);
                intent.putExtra("id",post.getUser_id());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        };
        holder.circleImageView.setOnClickListener(click);
        holder.textView.setOnClickListener(click);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView circleImageView;
        TextView textView;
        TextView title;
        TextView content;
        TextView like_size;
        TextView collects_size;
        TextView comments_size;
        ImageView like;
        ImageView collects;
        ImageView comments;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.card_post_home_circle);
            textView = itemView.findViewById(R.id.card_post_home_text);
            title = itemView.findViewById(R.id.card_post_home_title);
            content = itemView.findViewById(R.id.card_post_home_content);
            like = itemView.findViewById(R.id.card_post_home_like);
            collects = itemView.findViewById(R.id.card_post_home_collects);
            comments = itemView.findViewById(R.id.card_post_home_comments);
            like_size = itemView.findViewById(R.id.like_size);
            collects_size = itemView.findViewById(R.id.collects_size);
            comments_size = itemView.findViewById(R.id.comments_size);
            recyclerView = itemView.findViewById(R.id.card_post_home_rec);
        }
    }
}
