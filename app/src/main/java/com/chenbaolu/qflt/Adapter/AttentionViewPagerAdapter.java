package com.chenbaolu.qflt.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.qflt.CallBack.CurrentCallBack;
import com.chenbaolu.qflt.R;

import java.util.List;

/**
 * 描述 :
 * 创建时间 : 2022/9/12 20:53
 * 作者 : 23128
 */
public class AttentionViewPagerAdapter extends RecyclerView.Adapter<AttentionViewPagerAdapter.ViewHolder> {
    private Context context;
    private List<List<UserData>> list;
    private CurrentCallBack currentCallBack;
    public AttentionViewPagerAdapter(Context context,List<List<UserData>> list, CurrentCallBack currentCallBack) {
        this.list = list;
        this.currentCallBack = currentCallBack;
        this.context =context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewpager_attention,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        AttentionRecyclerViewAdapter adapter = new AttentionRecyclerViewAdapter(context,list.get(position));
        holder.recyclerView.setAdapter(adapter);
        holder.swipeRefreshLayout.setTag("a_swipe_"+position);
        holder.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentCallBack.refresh(holder.getAdapterPosition(),0,0,adapter);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;
        SwipeRefreshLayout swipeRefreshLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            swipeRefreshLayout = itemView.findViewById(R.id.attention_v_swipe);
            recyclerView = itemView.findViewById(R.id.attention_v_rec);
        }
    }
}
