package com.chenbaolu.qflt.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chenbaolu.baselib.network.bean.pojo.ImageStatus;
import com.chenbaolu.qflt.CallBack.ListCallBack;
import com.chenbaolu.qflt.R;

import java.util.List;

/**
 * 描述 :
 * 创建时间 : 2022/9/17 20:44
 * 作者 : 23128
 */
public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder> {
    private List<ImageStatus> list;
    private Context context;
    private ListCallBack listCallBack;


    public ImageRecyclerViewAdapter(List<ImageStatus> list, Context context, ListCallBack onClickListener) {
        this.list = list;
        this.context = context;
        this.listCallBack = onClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageStatus imageStatus = list.get(position);
        Glide.with(context).load(imageStatus.getUri()).into(holder.imageView);
        if (imageStatus.getUrl() == null) {
            holder.textView.setText("正在上传");
        } else {
            holder.textView.setText("点击插入");
        }
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageStatus.getUrl()!=null){
                    listCallBack.click(imageStatus.getUrl());
                }
            }
        });
        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = list.indexOf(imageStatus);
                list.remove(index);
                ImageRecyclerViewAdapter.this.notifyItemRemoved(index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        ImageView close;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image_image);
            textView = itemView.findViewById(R.id.item_image_text);
            close = itemView.findViewById(R.id.item_image_close);
        }
    }
}
