package com.chenbaolu.qflt.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.baselib.network.bean.pojo.UserNews;
import com.chenbaolu.qflt.CustomizeView.DialogView;
import com.chenbaolu.qflt.R;

import java.util.List;
import java.util.Map;

/**
 * 描述 :
 * 创建时间 : 2022/9/16 15:57
 * 作者 : 23128
 */
public class DialogRecyclerAdapter extends RecyclerView.Adapter<DialogRecyclerAdapter.ViewHolder> {
    private List<UserNews> userNews;
    private Context context;
    private Map<Long,String> map;


    public DialogRecyclerAdapter(Context context, List<UserNews> userNews, Map<Long, String> map) {
        this.userNews = userNews;
        this.context = context;
        this.map = map;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dialog,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserNews news = userNews.get(userNews.size()-1-position);
        if (news.getUser_id()== BaseApplication.getUserId()){
            holder.relativeLayout_false.setVisibility(View.GONE);
            holder.relativeLayout_true.setVisibility(View.VISIBLE);
            holder.dialogView_true.setText(news.getContent());
            Glide.with(context).load(map.get(news.getUser_id())).into(holder.image_true);
        }else{
            holder.relativeLayout_true.setVisibility(View.GONE);
            holder.relativeLayout_false.setVisibility(View.VISIBLE);
            holder.dialogView_false.setText(news.getContent());
            Glide.with(context).load(map.get(news.getUser_id())).into(holder.image_false);
        }

    }

    @Override
    public int getItemCount() {
        return userNews.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image_false;
        ImageView image_true;
        DialogView dialogView_false;
        DialogView dialogView_true;
        RelativeLayout relativeLayout_false;
        RelativeLayout relativeLayout_true;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_false = itemView.findViewById(R.id.item_dialog_circle_false);
            image_true = itemView.findViewById(R.id.item_dialog_circle_true);
            dialogView_false = itemView.findViewById(R.id.item_dialog_dialog_false);
            dialogView_true = itemView.findViewById(R.id.item_dialog_dialog_true);
            relativeLayout_false = itemView.findViewById(R.id.item_dialog_false);
            relativeLayout_true = itemView.findViewById(R.id.item_dialog_true);
        }
    }

}
