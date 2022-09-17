package com.chenbaolu.qflt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.baselib.network.bean.pojo.PostCollects;
import com.chenbaolu.baselib.network.bean.pojo.PostComments;
import com.chenbaolu.baselib.network.bean.pojo.PostLike;
import com.chenbaolu.qflt.R;
import com.chenbaolu.qflt.ui.activity.PostDetailsActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 描述 :
 * 创建时间 : 2022/9/17 09:23
 * 作者 : 23128
 */
public class MinePostRecyclerAdapter extends RecyclerView.Adapter<MinePostRecyclerAdapter.ViewHolder> {
    private Context context;
    private List list;

    public MinePostRecyclerAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_message,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Long id = null;
        Object object = list.get(position);
        if (object instanceof PostLike){
            PostLike postLike = (PostLike) object;
            id = postLike.getPost_id();
            holder.data.setText("点赞了帖子: "+id);
        }else if(object instanceof PostCollects){
            PostCollects postComments = (PostCollects) object;
            id = postComments.getPost_id();
            holder.data.setText("收藏了帖子: "+id);
        }else{
            PostComments postComments = (PostComments) object;
            id = postComments.getPost_id();
            holder.data.setText(postComments.getTitle());
        }
        Glide.with(context).load(BaseApplication.getSharedPreferences().getString("avatar","")).into(holder.circleImageView);
        holder.name.setText("test");

        Long finalId = id;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PostDetailsActivity.class);
                intent.putExtra("id", finalId);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView circleImageView;
        TextView name;
        TextView data;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.card_message_name);
            circleImageView = itemView.findViewById(R.id.card_message_circle);
            data = itemView.findViewById(R.id.card_message_message);
        }
    }
}
