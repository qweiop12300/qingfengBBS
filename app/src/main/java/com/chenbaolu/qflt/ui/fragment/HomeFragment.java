package com.chenbaolu.qflt.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.SocketBean.Message;
import com.chenbaolu.baselib.network.bean.dto.PostCommentsDto;
import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.baselib.network.bean.pojo.PostType;
import com.chenbaolu.qflt.Adapter.HomeRecyclerViewAdapter;
import com.chenbaolu.qflt.Adapter.ViewPagerAdapter;
import com.chenbaolu.qflt.CallBack.CurrentCallBack;
import com.chenbaolu.qflt.MVP.Presenter.HomePresenter;
import com.chenbaolu.qflt.MVP.Presenter.Impl.HomePresenterImpl;
import com.chenbaolu.qflt.MyApplication;
import com.chenbaolu.qflt.R;
import com.chenbaolu.qflt.RxBus.RxBus;
import com.chenbaolu.qflt.ui.activity.LoginActivity;
import com.chenbaolu.qflt.ui.activity.MainActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * 描述 :
 * 创建时间 : 2022/9/7 21:02
 * 作者 : 23128
 */
@AndroidEntryPoint
public class HomeFragment extends Fragment implements HomePresenter.View {
    @Inject
    HomePresenter.Model model;

    ViewPager2 viewPager2;
    TabLayout tabLayout;
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        model.setModel(this);
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        init(getView());
    }

    public void init(View rootView){
        viewPager2= rootView.findViewById(R.id.home_viewpager);
        tabLayout = rootView.findViewById(R.id.home_tab);
        View view = rootView.findViewById(R.id.home_bar_newmessage);
        CircleImageView circleImageView = rootView.findViewById(R.id.home_bar_circle);
        SharedPreferences sharedPreferences = MyApplication.getSharedPreferences();
        String image = sharedPreferences.getString("avatar","");
        if(image!=null&&image!=""){
            Glide.with(this).load(image).into(circleImageView);
        }
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getToken().equals("")){
                    Intent intent = new Intent(HomeFragment.this.getContext(), LoginActivity.class);
                    startActivity(intent);
                }else{
                    MainActivity mainActivity = (MainActivity) getActivity();
                }
            }
        });
        RxBus.getInstance().toObservable(Message.class).subscribe(new Consumer<Message>() {
            @Override
            public void accept(Message message) throws Throwable {
                if(message.getType().equals("send")){
                    if (message.getData().getNews().size()>0){
                        view.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setVisibility(View.INVISIBLE);
            }
        });

        model.getPostType();
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initTabLayout(List<PostType> list) {
        List<List<Post>> listItem = new ArrayList<>();
        for (PostType postType : list){
            listItem.add(new ArrayList<Post>());
        }
        viewPager2.setAdapter(new ViewPagerAdapter(list, listItem, new CurrentCallBack() {
            @Override
            public void refresh(Integer pg, Integer pz, int typeId, RecyclerView.Adapter adapter) {
                model.getListPost(pg,pz,typeId,adapter);
            }
        }));
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                swipeRefreshLayout = viewPager2.findViewWithTag("swipe"+position);
            }
        });

        new TabLayoutMediator(tabLayout,viewPager2,new TabLayoutMediator.TabConfigurationStrategy(){
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(list.get(position).getTitle());
            }
        }).attach();
    }

    @Override
    public void updateRecyclerViewData(boolean isAdd, List<Post> posts, RecyclerView.Adapter adapter) {
        HomeRecyclerViewAdapter homeRecyclerViewAdapter = (HomeRecyclerViewAdapter) adapter;
        if(isAdd){
            int oldSize = homeRecyclerViewAdapter.getList().size();
            homeRecyclerViewAdapter.getList().addAll(posts);
            adapter.notifyItemRangeInserted(oldSize, homeRecyclerViewAdapter.getList().size());
        }else{
            homeRecyclerViewAdapter.setList(posts);
            homeRecyclerViewAdapter.notifyDataSetChanged();
        }
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
    public void onDestroy() {
        super.onDestroy();
        model.setModel(null);
        model=null;
    }

}
