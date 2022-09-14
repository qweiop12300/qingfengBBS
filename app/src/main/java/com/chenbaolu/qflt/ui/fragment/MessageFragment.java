package com.chenbaolu.qflt.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.baselib.network.bean.SocketBean.Message;
import com.chenbaolu.baselib.network.bean.pojo.UserNews;
import com.chenbaolu.qflt.Adapter.AttentionViewPagerAdapter;
import com.chenbaolu.qflt.Adapter.MessageViewPagerAdapter;
import com.chenbaolu.qflt.CallBack.CurrentCallBack;
import com.chenbaolu.qflt.MVP.Presenter.MessagePresenter;
import com.chenbaolu.qflt.R;
import com.chenbaolu.qflt.RxBus.RxBus;
import com.chenbaolu.qflt.SQLite.MyDBUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * 描述 :
 * 创建时间 : 2022/9/7 21:02
 * 作者 : 23128
 */
@AndroidEntryPoint
public class MessageFragment extends Fragment implements MessagePresenter.View {
    @Inject
    MessagePresenter.Model model;

    private List<List<UserNews>> newsList = new ArrayList<>();
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private MessageViewPagerAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        model.setModel(this);
        return inflater.inflate(R.layout.fragment_attention,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View rootView = getView();
        TextView textView = rootView.findViewById(R.id.default_action_bar_title);
        textView.setText("消息");
        tabLayout = rootView.findViewById(R.id.attention_tab);
        viewPager2 = rootView.findViewById(R.id.attention_viewpage2);
        List<String> list = new ArrayList<>();
        list.add("关注");
        list.add("评论");
        list.add("点赞");
        list.add("收藏");
        list.add("聊天");

        initData();

        adapter = new MessageViewPagerAdapter(getContext(), newsList, new CurrentCallBack() {
            @Override
            public void refresh(Integer pg, Integer pz, int typeId, RecyclerView.Adapter adapter) {
                swipeRefreshLayout.setRefreshing(true);
                initData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        viewPager2.setAdapter(adapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                swipeRefreshLayout = viewPager2.findViewWithTag("m_swipe_"+position);
            }
        });

        new TabLayoutMediator(tabLayout,viewPager2,new TabLayoutMediator.TabConfigurationStrategy(){
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(list.get(position));
            }
        }).attach();

        RxBus.getInstance().toObservable(Message.class).subscribe(new Consumer<Message>() {
            @Override
            public void accept(Message message) throws Throwable {
                if (message.getType().equals("send")){
                    for (UserNews userNews : message.getData().getNews()){
                        newsList.get((int) (userNews.getType()-1)).add(userNews);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void initData(){
        newsList.clear();
        newsList.add(MyDBUtil.getUserNewsList("un.type = ?",new String[]{"1"}));
        newsList.add(MyDBUtil.getUserNewsList("un.type = ?",new String[]{"2"}));
        newsList.add(MyDBUtil.getUserNewsList("un.type = ?",new String[]{"3"}));
        newsList.add(MyDBUtil.getUserNewsList("un.type = ?",new String[]{"4"}));
        newsList.add(MyDBUtil.getUserNewsList("un.type = ?",new String[]{"5"}));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dissLoading() {

    }

}
