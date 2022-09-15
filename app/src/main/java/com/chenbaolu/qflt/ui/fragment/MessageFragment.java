package com.chenbaolu.qflt.ui.fragment;

import android.os.Bundle;
import android.util.Log;
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
import com.chenbaolu.baselib.network.bean.pojo.User;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private Map<Long,UserData> userDataMap = new HashMap<>();
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private MessageViewPagerAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        model.setModel(this);
        View rootView = inflater.inflate(R.layout.fragment_attention,container,false);
        TextView textView = rootView.findViewById(R.id.default_action_bar_title);
        textView.setText("消息");
        tabLayout = rootView.findViewById(R.id.attention_tab);
        viewPager2 = rootView.findViewById(R.id.attention_viewpage2);
        initData();
        return rootView;
    }

    public void initData(){
        newsList.clear();
        newsList.add(MyDBUtil.getUserNewsTypeOne(String.valueOf(BaseApplication.getUserId())));
        newsList.add(MyDBUtil.getUserNewsList("type = ?",new String[]{"2"}));
        newsList.add(MyDBUtil.getUserNewsList("type = ?",new String[]{"3"}));
        newsList.add(MyDBUtil.getUserNewsList("type = ?",new String[]{"4"}));
        newsList.add(MyDBUtil.getUserNewsList("type = ?",new String[]{"5"}));
        Log.d("test1",newsList.get(0).toString());
        for (List<UserNews> userNewsList:newsList){
            for (UserNews userNews:userNewsList){
                userDataMap.put(userNews.getUser_id(),null);
                userDataMap.put(userNews.getProduce_user_id(),null);
            }
        }
        model.getUserDataList(userDataMap.keySet());

        RxBus.getInstance().toObservable(Message.class).subscribe(new Consumer<Message>() {
            @Override
            public void accept(Message message) throws Throwable {

                if(message.getType().equals("send")||message.getType().equals("mySend")){
                    if(message.getData().getNews().size()!=0){
                        UserNews userNews = message.getData().getNews().get(0);
                        if (message.getType().equals("send")){
                            if (userDataMap.containsKey(userNews.getUser_id())){
                                Log.d("test1","------------------------");
                                newsList.get((int) (userNews.getType()-1)).add(0,userNews);
                                adapter.notifyDataSetChanged();
                            }else{
                                model.getUserData(userNews.getUser_id(),userNews);
                            }
                        }else{
                            if (userDataMap.containsKey(userNews.getProduce_user_id())){
                                newsList.get((int) (userNews.getType()-1)).add(0,userNews);
                                adapter.notifyDataSetChanged();
                            }else {
                                model.getUserData(userNews.getProduce_user_id(),userNews);
                            }
                        }
                    }
                }
            }
        });
    }
    @Override
    public void showLoading() {
        if (swipeRefreshLayout!=null){
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void dissLoading() {
        if (swipeRefreshLayout!=null){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showUserDataList(List<UserData> list) {
        for (UserData userData :list){
            userDataMap.put(userData.getUser_id(),userData);
        }

        List<String> titleList = new ArrayList<>();
        titleList.add("聊天");
        titleList.add("点赞");
        titleList.add("评论");
        titleList.add("收藏");
        titleList.add("关注");

        adapter = new MessageViewPagerAdapter(getContext(), newsList, userDataMap,new CurrentCallBack() {
            @Override
            public void refresh(Integer pg, Integer pz, int typeId, RecyclerView.Adapter adapter) {
                initData();
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
                tab.setText(titleList.get(position));
            }
        }).attach();

    }

    @Override
    public void showUserData(UserData userData, UserNews userNews) {
        Log.d("test1","------");
        if (userData!=null){
            if (!userDataMap.containsKey(userData.getUser_id())){
                userDataMap.put(userData.getUser_id(),userData);
            }
            newsList.get((int) (userNews.getType()-1)).add(0,userNews);
            adapter.notifyDataSetChanged();
        }
    }
}
