package com.chenbaolu.qflt;


import android.content.Intent;
import android.os.Bundle;

public class LogoSplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this,MainActivity.class));

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}