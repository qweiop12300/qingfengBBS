package com.chenbaolu.qflt.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.pojo.PostType;
import com.chenbaolu.qflt.Adapter.ViewPagerAdapter;
import com.chenbaolu.qflt.MVP.Presenter.HomePresenter;
import com.chenbaolu.qflt.MVP.Presenter.Impl.HomePresenterImpl;
import com.chenbaolu.qflt.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onStart() {
        super.onStart();
        View rootView = getView();
        viewPager2= rootView.findViewById(R.id.home_viewpager);
        tabLayout = rootView.findViewById(R.id.home_tab);
        swipeRefreshLayout = rootView.findViewById(R.id.home_swipe);



        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (viewPager2.getScrollState()== ViewPager2.SCROLL_STATE_DRAGGING){
                    swipeRefreshLayout.setEnabled(false);
                }else {
                    swipeRefreshLayout.setEnabled(true);
                }
            }
        });

        model.setBaseView(this);
        model.getPostType();
    }

    @Override
    public void initTabLayout(List<PostType> list) {
        viewPager2.setAdapter(new ViewPagerAdapter(list));
        new TabLayoutMediator(tabLayout,viewPager2,new TabLayoutMediator.TabConfigurationStrategy(){
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(list.get(position).getTitle());
            }
        }).attach();
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void dissLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setModel(BasePresenter.BaseModel baseModel) {
        model = (HomePresenter.Model) baseModel;
    }
}
