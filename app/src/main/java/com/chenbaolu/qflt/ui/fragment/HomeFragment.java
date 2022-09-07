package com.chenbaolu.qflt.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.chenbaolu.qflt.Adapter.ViewPagerAdapter;
import com.chenbaolu.qflt.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 :
 * 创建时间 : 2022/9/7 21:02
 * 作者 : 23128
 */
public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View rootView = getView();
        ViewPager2 viewPager2 = rootView.findViewById(R.id.home_viewpager);
        TabLayout tabLayout = rootView.findViewById(R.id.home_tab);
        List<String> list = new ArrayList<>();
        list.add("全部");
        list.add("分组1");
        list.add("分组2");
        viewPager2.setAdapter(new ViewPagerAdapter(list));
        new TabLayoutMediator(tabLayout,viewPager2,new TabLayoutMediator.TabConfigurationStrategy(){
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(list.get(position));
            }
        }).attach();
    }
}
