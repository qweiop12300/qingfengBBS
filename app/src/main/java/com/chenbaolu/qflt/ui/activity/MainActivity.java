package com.chenbaolu.qflt.ui.activity;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.chenbaolu.baselib.base.BaseActivity;
import com.chenbaolu.qflt.MyApplication;
import com.chenbaolu.qflt.R;
import com.chenbaolu.qflt.ui.fragment.AttentionFragment;
import com.chenbaolu.qflt.ui.fragment.HomeFragment;
import com.chenbaolu.qflt.ui.fragment.MessageFragment;
import com.chenbaolu.qflt.ui.fragment.MineFragment;
import com.chenbaolu.qflt.ui.service.MessageService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

@AndroidEntryPoint
public class MainActivity extends BaseActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    protected void init(){
        BottomNavigationView navView = findViewById(R.id.main_bottom);

        HomeFragment homeFragment = new HomeFragment();
        MineFragment mineFragment = new MineFragment();
        MessageFragment messageFragment = new MessageFragment();
        AttentionFragment attentionFragment = new AttentionFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.main_fragment,homeFragment)
                .add(R.id.main_fragment,mineFragment)
                .add(R.id.main_fragment,messageFragment)
                .add(R.id.main_fragment,attentionFragment)
                .show(homeFragment)
                .hide(mineFragment)
                .hide(messageFragment)
                .hide(attentionFragment)
                .commit();


        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                        .hide(homeFragment)
                        .hide(mineFragment)
                        .hide(messageFragment)
                        .hide(attentionFragment);
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        fragmentTransaction.show(homeFragment);
                        break;
                    case R.id.navigation_attention:
                        fragmentTransaction.show(attentionFragment);
                        break;
                    case R.id.navigation_message:
                        fragmentTransaction.show(messageFragment);
                        break;

                    case R.id.navigation_mine:
                        fragmentTransaction.show(mineFragment);
                        break;
                }
                fragmentTransaction.commit();
                return true;
            }
        });

        String token = MyApplication.getToken();
        if (token!=null&&!token.equals("")){
            intent = new Intent(this, MessageService.class);
            startService(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
    }
}