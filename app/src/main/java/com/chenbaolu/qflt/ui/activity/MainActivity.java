package com.chenbaolu.qflt.ui.activity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.chenbaolu.baselib.base.BaseActivity;
import com.chenbaolu.qflt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {
    NavController navController;

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
    }

    public void navigate(int id){
        navController.navigate(id);
    }

}