package com.chenbaolu.qflt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.chenbaolu.qflt.Adapter.RecyclerViewAdapter;
import com.chenbaolu.qflt.Adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPager();
    }

    protected void initViewPager(){
        ViewPager2 viewPager2 = findViewById(R.id.main_viewpager);
        TabLayout tabLayout = findViewById(R.id.main_tab);
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