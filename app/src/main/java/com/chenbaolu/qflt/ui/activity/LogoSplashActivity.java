package com.chenbaolu.qflt.ui.activity;


import android.content.Intent;
import android.os.Bundle;

import com.chenbaolu.baselib.base.BaseActivity;


public class LogoSplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, MainActivity.class));

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}