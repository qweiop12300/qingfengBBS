package com.chenbaolu.qflt.ui.activity;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MenuItem;
import android.widget.Toast;

import com.chenbaolu.baselib.BaseApplication;
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

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

@AndroidEntryPoint
public class MainActivity extends BaseActivity {

    ServiceConnection serviceConnection;

    FragmentManager fragmentManager;

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

         fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.main_fragment,homeFragment,"f_1")
                .add(R.id.main_fragment,mineFragment,"f_2")
                .add(R.id.main_fragment,messageFragment,"f_3")
                .add(R.id.main_fragment,attentionFragment,"f_4")
                .show(homeFragment)
                .hide(mineFragment)
                .hide(messageFragment)
                .hide(attentionFragment)
                .commit();


        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Long id = BaseApplication.getUserId();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                        .hide(homeFragment)
                        .hide(mineFragment)
                        .hide(messageFragment)
                        .hide(attentionFragment);
                if (id==0){
                    fragmentTransaction.show(homeFragment).commit();
                    Toast.makeText(MainActivity.this, "请登录", Toast.LENGTH_SHORT).show();
                    return false;
                }
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

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Toast.makeText(MainActivity.this, "服务创建成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        String token = MyApplication.getToken();
        if (token!=null&&!token.equals("")){
            Intent intent = new Intent(this, MessageService.class);
            bindService(intent,serviceConnection ,BIND_AUTO_CREATE);
        }

    }

    public void showFragment(int index){
        List<Fragment> list = fragmentManager.getFragments();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        for (Fragment fragment :list){
            fragmentTransaction.hide(fragment);
        }
        fragmentTransaction.show(fragmentManager.findFragmentByTag("f_"+index));
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}