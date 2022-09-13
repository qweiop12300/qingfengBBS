package com.chenbaolu.qflt.ui.activity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.chenbaolu.baselib.base.BaseActivity;
import com.chenbaolu.baselib.network.bean.SocketBean.Message;
import com.chenbaolu.qflt.MyApplication;
import com.chenbaolu.qflt.R;
import com.chenbaolu.qflt.RxBus.RxBus;
import com.chenbaolu.qflt.ui.service.MessageService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

@AndroidEntryPoint
public class MainActivity extends BaseActivity {
    NavController navController;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    protected void init(){
        BottomNavigationView navView = findViewById(R.id.main_bottom);
        navController = Navigation.findNavController(this, R.id.main_nav_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        String token = MyApplication.getToken();
        if (token!=null&&!token.equals("")){
            intent = new Intent(this, MessageService.class);
            startService(intent);
        }
    }

    public void navigate(int id){
        navController.navigate(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
    }
}