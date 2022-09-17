package com.chenbaolu.qflt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chenbaolu.baselib.network.bean.pojo.User;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.qflt.R;
import com.chenbaolu.qflt.ui.activity.UserDetailActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 描述 :
 * 创建时间 : 2022/9/12 21:04
 * 作者 : 23128
 */
public class AttentionRecyclerViewAdapter extends RecyclerView.Adapter<AttentionRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<UserData> list;

    public AttentionRecyclerViewAdapter(Context context,List<UserData> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_user_data,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserData userData = list.get(position);
        Glide.with(context).load(userData.getAvatar()).into(holder.circleImageView);
        holder.textView.setText(userData.getName());
        View.OnClickListener click = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserDetailActivity.class);
                intent.putExtra("id",userData.getUser_id());
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

    static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView circleImageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.card_user_data_circle);
            textView = itemView.findViewById(R.id.card_user_data_name);
        }
    }

}
