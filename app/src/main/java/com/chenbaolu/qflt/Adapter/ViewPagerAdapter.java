package com.chenbaolu.qflt.Adapter;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.qflt.CallBack.CurrentCallBack;
import com.chenbaolu.qflt.R;
import com.chenbaolu.baselib.network.bean.pojo.PostType;

import java.util.List;

/**
 * 描述 :
 * 创建时间 : 2022/9/4 19:37
 * 作者 : 23128
 */
public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    List<PostType> titles;
    List<List<Post>> listItem;
    CurrentCallBack currentCallBack;


    public ViewPagerAdapter(List<PostType> titles, List<List<Post>> listItem, CurrentCallBack currentCallBack) {
        this.titles = titles;
        this.listItem = listItem;
        this.currentCallBack = currentCallBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewpager_home,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RecyclerView recyclerView = holder.recyclerView;
        SwipeRefreshLayout swipeRefreshLayout = holder.refreshLayout;
        swipeRefreshLayout.setTag("swipe"+position);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        HomeRecyclerViewAdapter homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(listItem.get(position));
        recyclerView.setAdapter(homeRecyclerViewAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh(swipeRefreshLayout,holder.getAdapterPosition(), homeRecyclerViewAdapter);
            }
        });
        Refresh(swipeRefreshLayout,holder.getAdapterPosition(), homeRecyclerViewAdapter);
    }

    public void Refresh(SwipeRefreshLayout swipeRefreshLayout, int position, HomeRecyclerViewAdapter homeRecyclerViewAdapter){
        currentCallBack.refresh(0,20, (int) titles.get(position).getId(), homeRecyclerViewAdapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        },2000);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;
        SwipeRefreshLayout refreshLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.home_fragment_recycler);
            refreshLayout = (SwipeRefreshLayout) itemView.findViewById(R.id.home_fragment_swipeRefresh);
        }
    }
}
