package com.chenbaolu.qflt.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.qflt.Adapter.AttentionRecyclerViewAdapter;
import com.chenbaolu.qflt.Adapter.AttentionViewPagerAdapter;
import com.chenbaolu.qflt.CallBack.CurrentCallBack;
import com.chenbaolu.qflt.MVP.Presenter.AttentionPresenter;
import com.chenbaolu.qflt.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * 描述 :
 * 创建时间 : 2022/9/7 20:52
 * 作者 : 23128
 */
@AndroidEntryPoint
public class AttentionFragment extends Fragment implements AttentionPresenter.View {
    @Inject
    AttentionPresenter.Model model;

    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private List<List<UserData>> userList = new ArrayList<>();

    private AttentionViewPagerAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        model.setModel(this);
        userList.add(new ArrayList<>());;
        userList.add(new ArrayList<>());
        return inflater.inflate(R.layout.fragment_attention,container,false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View rootView = getView();
        TextView textView = rootView.findViewById(R.id.default_action_bar_title);
        textView.setText("关注");
        tabLayout = rootView.findViewById(R.id.attention_tab);
        viewPager2 = rootView.findViewById(R.id.attention_viewpage2);
        List<String> list = new ArrayList<>();
        list.add("关注");
        list.add("粉丝");
        adapter = new AttentionViewPagerAdapter(getContext(), userList, new CurrentCallBack() {
            @Override
            public void refresh(Integer pg, Integer pz, int typeId, RecyclerView.Adapter adapter) {
                model.getAttention(pg);
            }
        });
        viewPager2.setAdapter(adapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                swipeRefreshLayout = viewPager2.findViewWithTag("a_swipe_"+position);
            }
        });

        new TabLayoutMediator(tabLayout,viewPager2,new TabLayoutMediator.TabConfigurationStrategy(){
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(list.get(position));
            }
        }).attach();

        model.getAttention(0);
        model.getAttention(1);

    }

    @Override
    public void showLoading() {
        if(swipeRefreshLayout!=null){
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void dissLoading() {
        if(swipeRefreshLayout!=null){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showAttention(List<UserData> list) {
        if(list!=null){
            userList.set(0,list);
        }
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void showFan(List<UserData> list) {
        if(list!=null){
            userList.set(1,list);
        }
        this.adapter.notifyDataSetChanged();
    }
}
