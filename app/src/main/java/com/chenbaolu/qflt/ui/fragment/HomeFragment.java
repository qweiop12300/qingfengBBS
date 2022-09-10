package com.chenbaolu.qflt.ui.fragment;

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
import com.chenbaolu.baselib.network.bean.pojo.Post;
import com.chenbaolu.baselib.network.bean.pojo.PostType;
import com.chenbaolu.qflt.Adapter.RecyclerViewAdapter;
import com.chenbaolu.qflt.Adapter.ViewPagerAdapter;
import com.chenbaolu.qflt.CallBack.OnRefreshCallBack;
import com.chenbaolu.qflt.MVP.Presenter.HomePresenter;
import com.chenbaolu.qflt.MVP.Presenter.Impl.HomePresenterImpl;
import com.chenbaolu.qflt.MyApplication;
import com.chenbaolu.qflt.R;
import com.chenbaolu.qflt.ui.activity.LoginActivity;
import com.chenbaolu.qflt.ui.activity.MainActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 描述 :
 * 创建时间 : 2022/9/7 21:02
 * 作者 : 23128
 */
public class HomeFragment extends Fragment implements HomePresenter.View {

    HomePresenter.Model model = new HomePresenterImpl();
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View rootView = getView();
        viewPager2= rootView.findViewById(R.id.home_viewpager);
        tabLayout = rootView.findViewById(R.id.home_tab);
        CircleImageView circleImageView = rootView.findViewById(R.id.home_bar_circle);
        SharedPreferences sharedPreferences = MyApplication.getSharedPreferences();
        String image = sharedPreferences.getString("avatar","");
        if(image!=null&&image!=""){
            Glide.with(this).load(image).into(circleImageView);
        }
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getToken()==""){
                    Intent intent = new Intent(HomeFragment.this.getContext(), LoginActivity.class);
                    startActivity(intent);
                }else{
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.navigate(R.id.navigation_mine);
                }
            }
        });

        model.setBaseView(this);
        model.getPostType();
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initTabLayout(List<PostType> list) {
        List<List<Post>> listItem = new ArrayList<>();
        for (PostType postType : list){
            listItem.add(new ArrayList<Post>());
        }
        viewPager2.setAdapter(new ViewPagerAdapter(list, listItem, new OnRefreshCallBack() {
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
        RecyclerViewAdapter recyclerViewAdapter = (RecyclerViewAdapter) adapter;
        if(isAdd){
            int oldSize = recyclerViewAdapter.getList().size();
            recyclerViewAdapter.getList().addAll(posts);
            adapter.notifyItemRangeInserted(oldSize,recyclerViewAdapter.getList().size());
        }else{
            recyclerViewAdapter.setList(posts);
            recyclerViewAdapter.notifyDataSetChanged();
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
    public void setModel(BasePresenter.BaseModel baseModel) {
        model = (HomePresenter.Model) baseModel;
    }
}
