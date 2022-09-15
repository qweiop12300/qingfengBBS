package com.chenbaolu.qflt.Adapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.baselib.network.bean.pojo.UserNews;
import com.chenbaolu.qflt.CallBack.CurrentCallBack;
import com.chenbaolu.qflt.R;

import java.util.List;
import java.util.Map;

/**
 * 描述 :
 * 创建时间 : 2022/9/12 20:53
 * 作者 : 23128
 */
public class MessageViewPagerAdapter extends RecyclerView.Adapter<MessageViewPagerAdapter.ViewHolder> {
    private Context context;
    private List<List<UserNews>> list;
    private CurrentCallBack currentCallBack;
    private Map<Long,UserData> userDataMap;
    public MessageViewPagerAdapter(Context context, List<List<UserNews>> list, Map<Long,UserData> userDataMap, CurrentCallBack currentCallBack) {
        this.list = list;
        this.currentCallBack = currentCallBack;
        this.context =context;
        this.userDataMap = userDataMap;
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
        MessageRecyclerViewAdapter adapter = new MessageRecyclerViewAdapter(context,list.get(position),userDataMap);
        holder.recyclerView.setAdapter(adapter);
        holder.swipeRefreshLayout.setTag("m_swipe_"+position);
        holder.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentCallBack.refresh(holder.getAdapterPosition(),0,0,adapter);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        holder.swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
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
